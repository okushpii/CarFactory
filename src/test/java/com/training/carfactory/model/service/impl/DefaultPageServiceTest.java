package com.training.carfactory.model.service.impl;

import com.training.carfactory.model.service.context.PageContext;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class DefaultPageServiceTest {

    private DefaultPageService defaultPageService;

    private PageContext pageContext;

    @Before
    public void setUp(){
        pageContext = PageContext.getInstance();
        defaultPageService = new DefaultPageService();
        pageContext.setNodes(new LinkedList<>(Arrays.
                asList(new AnchorPane(), new AnchorPane(), new AnchorPane())));
    }

    @Test
    public void shouldInitializePages(){
        AnchorPane pane1 = new AnchorPane();
        AnchorPane pane2 = new AnchorPane();
        defaultPageService.initializePages(pane1, pane2);
        assertEquals(Arrays.asList(pane1, pane2), pageContext.getNodes());
    }

    @Test
    public void shouldGetCurrentPage(){
        pageContext.setCurrentNodeIndex(0);
        defaultPageService.getCurrentPage();
        assertEquals(0, pageContext.getCurrentNodeIndex());
    }

    @Test
    public void shouldGetNextPage(){
        pageContext.setCurrentNodeIndex(0);
        defaultPageService.getNextPage();
        assertEquals(1, pageContext.getCurrentNodeIndex());
    }

    @Test
    public void shouldGetNextPageWhenIndexIsBigger(){
        pageContext.setCurrentNodeIndex(3);
        defaultPageService.getNextPage();
        assertEquals(0, pageContext.getCurrentNodeIndex());
    }

    @Test
    public void shouldGetPreviousPage(){
        pageContext.setCurrentNodeIndex(1);
        defaultPageService.getPreviousPage();
        assertEquals(0, pageContext.getCurrentNodeIndex());
    }
}