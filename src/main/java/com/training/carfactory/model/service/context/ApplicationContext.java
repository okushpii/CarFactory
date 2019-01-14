package com.training.carfactory.model.service.context;

import com.training.carfactory.controller.ApplicationController;
import com.training.carfactory.controller.facade.ApplicationFacade;
import com.training.carfactory.model.dao.BodyDao;
import com.training.carfactory.model.dao.EngineDao;
import com.training.carfactory.model.dao.impl.DefaultBodyDao;
import com.training.carfactory.model.dao.impl.DefaultEngineDao;
import com.training.carfactory.model.dao.util.ConnectionFactory;
import com.training.carfactory.model.service.BodyService;
import com.training.carfactory.model.service.ElementService;
import com.training.carfactory.model.service.EngineService;
import com.training.carfactory.model.service.PageService;
import com.training.carfactory.model.service.impl.DefaultBodyService;
import com.training.carfactory.model.service.impl.DefaultElementService;
import com.training.carfactory.model.service.impl.DefaultEngineService;
import com.training.carfactory.model.service.impl.DefaultPageService;

public class ApplicationContext {

    private static ApplicationContext instance;
    private ApplicationFacade applicationFacade;

    private ApplicationContext() {
        initContext();
    }

    private void initContext() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        EngineDao engineDao = new DefaultEngineDao(connectionFactory);
        EngineService engineService = new DefaultEngineService(engineDao);
        BodyDao bodyDao = new DefaultBodyDao(connectionFactory);
        BodyService bodyService = new DefaultBodyService(bodyDao);
        PageContext pageContext = new PageContext();
        PageService pageService = new DefaultPageService(pageContext);
        ElementService elementService = new DefaultElementService(bodyService);
        applicationFacade = new ApplicationFacade(pageService, elementService);

    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public void initController(ApplicationController controller){
        controller.setApplicationFacade(applicationFacade);
    }
}
