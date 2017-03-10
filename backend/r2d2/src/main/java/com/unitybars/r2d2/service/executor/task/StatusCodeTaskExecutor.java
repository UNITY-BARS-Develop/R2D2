package com.unitybars.r2d2.service.executor.task;

import com.unitybars.r2d2.entity.*;
import com.unitybars.r2d2.exception.MissedParameterException;
import com.unitybars.r2d2.exception.RequestExecuteError;
import com.unitybars.r2d2.service.RequestService;
import com.unitybars.r2d2.utils.ParametersExtractor;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import java.util.Date;


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
    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public TaskCheckLog doCheck() throws MissedParameterException, RequestExecuteError {
        Response response = requestService.executeResponse(
                ParametersExtractor.getServiceParameter(service, ServiceTypeParameter.URL),
                ParametersExtractor.getTaskRequestMethod(task),
                ParametersExtractor.getTaskHeaders(task));
        return parseResponse(response);
    }

    private TaskCheckLog parseResponse(Response response) {
        String statusCode = String.valueOf(response.code());
        return new TaskCheckLog(0, task.getName(), task.getTaskTypeId().name(), task.getExpectedValue(), statusCode,
                new Date(), getStatus(response), 0, null);
    }

    private CheckStatus getStatus(Response response) {
        if (task.getExpectedValue().equals(String.valueOf(response.code()))) {
            return CheckStatus.SUCCESS;
        }
        return CheckStatus.ERROR;
    }
}