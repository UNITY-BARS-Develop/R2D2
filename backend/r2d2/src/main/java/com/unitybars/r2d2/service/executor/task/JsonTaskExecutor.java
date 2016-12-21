package com.unitybars.r2d2.service.executor.task;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

import java.io.IOException;
import java.util.Date;

/**
 * Created by oleg.nestyuk
 * Date: 16-Dec-16.
 */

@org.springframework.stereotype.Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JsonTaskExecutor implements TaskExecutor {

    @Autowired
    private RequestService requestService;

    Logger logger = LoggerFactory.getLogger(JsonTaskExecutor.class);
    private Task task;
    private Service service;
    private TaskCheckLog taskCheckLog;

    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

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
        logger.info(ParametersExtractor.getServiceParameter(service, ServiceTypeParameter.URL));
        Response response = requestService.executeResponse(
                ParametersExtractor.getServiceParameter(service, ServiceTypeParameter.URL),
                ParametersExtractor.getTaskRequestMethod(task),
                ParametersExtractor.getTaskHeaders(task));
        return parseResponse(response);
    }

    private TaskCheckLog parseResponse(Response response) throws MissedParameterException {
        try {
            String responseJsonStr = response.body().string();
            logger.info(responseJsonStr);
            JsonObject responseJson = parseJson(responseJsonStr);
            return new TaskCheckLog(0, task.getName(), task.getTaskType().name(), task.getExpectedValue(),
                    getResultValue(responseJson), new Date(), getStatus(responseJson), 0);
        } catch (IOException e) {
            logger.error("Error happened when try to parse response body", e);
            return getErrorTaskCheckLog();
        }
    }

    private CheckStatus getStatus(JsonObject jsonObject) throws MissedParameterException {
        String jsonFieldName = ParametersExtractor.getTaskJsonFieldName(task);
        String resultValue = jsonObject.get(jsonFieldName).getAsString();
        return resultValue.equals(task.getExpectedValue()) ? CheckStatus.SUCCESS : CheckStatus.ERROR;
    }

    private String getResultValue(JsonObject jsonObject) throws MissedParameterException {
        return jsonObject.get(ParametersExtractor.getTaskJsonFieldName(task)).getAsString();
    }

    private JsonObject parseJson(String json) {
        JsonParser jsonParser = new JsonParser();
        return (JsonObject) jsonParser.parse(json);
    }

    private TaskCheckLog getErrorTaskCheckLog() {
        new TaskCheckLog(0, task.getName(), task.getTaskType().name(), task.getExpectedValue(),
                null, new Date(), CheckStatus.ERROR, 0);
        return null;
    }
}