package com.training.carfactory.model.service.context;

import com.training.carfactory.controller.ApplicationController;
import com.training.carfactory.model.dao.BodyDao;
import com.training.carfactory.model.dao.impl.DefaultBodyDao;
import com.training.carfactory.model.dao.util.ConnectionFactory;
import com.training.carfactory.model.service.BodyService;
import com.training.carfactory.model.service.PageService;
import com.training.carfactory.model.service.impl.DefaultBodyService;
import com.training.carfactory.model.service.impl.DefaultPageService;

public class ApplicationContext {

    private static ApplicationContext instance;
    private BodyService bodyService;
    private PageService pageService;

    private ApplicationContext() {
        initContext();
    }

    private void initContext() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        BodyDao bodyDao = new DefaultBodyDao(connectionFactory);
        bodyService = new DefaultBodyService(bodyDao);
        PageContext pageContext = new PageContext();
        pageService = new DefaultPageService(pageContext);
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public void initController(ApplicationController controller){
        controller.setBodyService(bodyService);
        controller.setPageService(pageService);
    }
}
