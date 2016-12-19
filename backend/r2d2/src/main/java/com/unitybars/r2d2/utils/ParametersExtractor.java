package com.unitybars.r2d2.utils;

import com.unitybars.r2d2.constants.Constants;
import com.unitybars.r2d2.entity.*;
import com.unitybars.r2d2.exception.MissedParameterException;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by oleg.nestyuk
 * Date: 19-Dec-16.
 */
public class ParametersExtractor {

    public static String getTaskJsonFieldName(Task task) throws MissedParameterException {
        List<TaskFieldValue> requestMethodValues = getTaskFieldValues(task, Constants.TaskTypeFieldConstants.JSON_FIELD_NAME);
        if (requestMethodValues.size() > 0) {
            return requestMethodValues.get(0).getValue();
        } else {
            throw new MissedParameterException("json field name");
        }
    }

    public static RequestMethod getTaskRequestMethod(Task task) throws MissedParameterException {
        List<TaskFieldValue> requestMethodValues = getTaskFieldValues(task, Constants.TaskTypeFieldConstants.REQUEST_METHOD);
        if (requestMethodValues.size() > 0) {
            return RequestMethod.getRequestMethod(requestMethodValues.get(0).getValue());
        } else {
            throw new MissedParameterException("request method");
        }
    }

    public static List<HeaderItem> getTaskHeaders(Task task) {
        List<TaskFieldValue> headerValues = getTaskFieldValues(task, Constants.TaskTypeFieldConstants.HEADER);
        List<HeaderItem> headerItems = new ArrayList<>();
        if (headerValues.size() > 0) {
            for (TaskFieldValue taskFieldValue : headerValues) {
                headerItems.add(new HeaderItem(taskFieldValue.getTaskTypeField().getName(), taskFieldValue.getValue()));
            }
        }
        return headerItems;
    }

    public static String getServiceParameter(Service service, ServiceTypeParameter parameter)
            throws MissedParameterException {
        if (service.getParameters() != null) {
            String parameterValue = service.getParameters().get(parameter);
            if (parameterValue == null) {
                throw new MissedParameterException(parameter.name());
            }
            return parameterValue;
        }
        throw new MissedParameterException(parameter.name());
    }

    protected static List<TaskFieldValue> getTaskFieldValues(Task task, String parameterName) {
        if (task.getFields() != null) {
            return task.getFields().stream()
                    .filter(t -> t.getTaskTypeField().getName().equalsIgnoreCase(parameterName))
                    .collect(toList());
        } else {
            return new ArrayList<>();
        }
    }
}