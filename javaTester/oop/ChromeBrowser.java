package oop;

import org.openqa.selenium.interactions.Actions;

public class ChromeBrowser extends Browser implements IBrowser {
    Actions actions;

    public void endUser() {
        openUrl();
        back();
        refresh();
        System.out.println(browserVersion);

    }

//    @Override
//    public void opeURL() {
//        actions.click();
//
//    }

//    @Override
//    public void back() {
//
//    }

//    @Override
//    public void refresh() {
//
//    }
}
