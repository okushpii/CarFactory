package com.training.carfactory.controller.context;

import com.training.carfactory.controller.ApplicationController;
import com.training.carfactory.controller.facade.ApplicationFacade;
import com.training.carfactory.controller.facade.CarFacade;
import com.training.carfactory.model.dao.BodyDao;
import com.training.carfactory.model.dao.CarDao;
import com.training.carfactory.model.dao.EngineDao;
import com.training.carfactory.model.dao.WheelsDao;
import com.training.carfactory.model.dao.impl.DefaultBodyDao;
import com.training.carfactory.model.dao.impl.DefaultCarDao;
import com.training.carfactory.model.dao.impl.DefaultEngineDao;
import com.training.carfactory.model.dao.impl.DefaultWheelsDao;
import com.training.carfactory.model.dao.util.ConnectionFactory;
import com.training.carfactory.model.service.*;
import com.training.carfactory.model.service.impl.*;
import com.training.carfactory.model.service.impl.util.*;

public class ApplicationContext {

    private static ApplicationContext instance;
    private ApplicationFacade applicationFacade;
    private CarFacade carFacade;

    private ApplicationContext() {
        initContext();
    }

    private void initContext() {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        WheelsDao wheelsDao = new DefaultWheelsDao(connectionFactory);
        WheelsService wheelsService = new DefaultWheelsService(wheelsDao);
        EngineDao engineDao = new DefaultEngineDao(connectionFactory);
        EngineService engineService = new DefaultEngineService(engineDao);
        BodyDao bodyDao = new DefaultBodyDao(connectionFactory);
        CarDao carDao = new DefaultCarDao(connectionFactory);

        BodyService bodyService = new DefaultBodyService(bodyDao);
        PageContext pageContext = new PageContext();
        PageService pageService = new DefaultPageService(pageContext);
        CarService carService = new DefaultCarService(carDao);

        PriceCalculationService priceCalculationService = new PriceCalculationService();
        ValueFormatterService valueFormatterService = new ValueFormatterService();
        ProgressBarSimulator progressBarSimulator = new ProgressBarSimulator();
        PartVerifier partVerifier = new PartVerifier();
        CarPropertyUpdater carPropertyUpdater = new CarPropertyUpdater(bodyService, engineService, wheelsService);
        CarProgressService carProgressService = new CarProgressService(progressBarSimulator, carPropertyUpdater);

        ElementService elementService = new DefaultElementService(bodyService, engineService, wheelsService, carService, valueFormatterService);

        applicationFacade = new ApplicationFacade(pageService, elementService, bodyService, engineService, wheelsService);
        carFacade = new CarFacade(bodyService, engineService, wheelsService,
                carService, priceCalculationService, partVerifier, carProgressService);
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public void initController(ApplicationController controller){
        controller.setApplicationFacade(applicationFacade);
        controller.setCarFacade(carFacade);
    }
}
