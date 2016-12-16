package com.unitybars.r2d2.service.executor.task;

import com.unitybars.r2d2.entity.*;
import com.unitybars.r2d2.exception.MissedServiceParameterException;
import com.unitybars.r2d2.exception.RequestExecuteError;
import com.unitybars.r2d2.service.RequestService;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */
@org.springframework.stereotype.Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StatusCodeTaskExecutor implements TaskExecutor {
    private Logger logger = LoggerFactory.getLogger(StatusCodeTaskExecutor.class);
    @Autowired
    private RequestService requestService;
    private Task task;
    private Service service;
    private TaskCheckLog taskCheckLog;

    @Override
    public void setTask(@NotNull Task task) {
        this.task = task;
    }

    @Override
    public void setService(@NotNull Service service) {
        this.service = service;
    }

    @Override
    public TaskCheckLog doCheck() throws MissedServiceParameterException, RequestExecuteError {
        Response response = requestService.executeResponse(getUrl(), getRequestMethod(), getHeaders());
        return parseResponse(response);
    }

    private TaskCheckLog parseResponse(Response response) {
        String statusCode = String.valueOf(response.code());
        return new TaskCheckLog(0, task.getName(), task.getTaskType().name(), task.getExpectedValue(), statusCode,
                new Date(), getStatus(response), 0);
    }

    private CheckStatus getStatus(Response response) {
        if (task.getExpectedValue().equals(String.valueOf(response.code()))) {
            return CheckStatus.SUCCESS;
        }
        return CheckStatus.ERROR;

    }

    private String getUrl() throws MissedServiceParameterException {
        String url = service.getParameters().get("url");
        if (url != null) {
            return url;
        } else {
            throw new MissedServiceParameterException();
        }
    }

    private RequestMethod getRequestMethod() {
        List<TaskFieldValue> requestMethodValues = task.getFields().stream()
                .filter(t -> t.getTaskTypeField().getName().equalsIgnoreCase("Request method"))
                .collect(toList());
        try {
            if (requestMethodValues.size() > 0) {
                return RequestMethod.getRequestMethod(requestMethodValues.get(0).getValue());
            } else {
                throw new MissedServiceParameterException();
            }
        } catch (Exception e) {
            logger.error("Can't find request method for task. Used default method -> GET");
            return RequestMethod.GET;
        }
    }

    private List<HeaderItem> getHeaders() {
        List<TaskFieldValue> headerValues = task.getFields().stream()
                .filter(t -> t.getTaskTypeField().getName().equalsIgnoreCase("header"))
                .collect(toList());
        List<HeaderItem> headerItems = new ArrayList<>();
        if (headerValues.size() > 0) {
            for (TaskFieldValue taskFieldValue : headerValues) {
                headerItems.add(new HeaderItem(taskFieldValue.getTaskTypeField().getName(), taskFieldValue.getValue()));
            }
        }
        return headerItems;
    }
}
