package com.viacom.test.alias.uitests.tests.userpreferences.profile;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.viacom.test.alias.common.BaseTest;
import com.viacom.test.alias.common.util.props.IProps.ConfigProps;
import com.viacom.test.alias.common.util.props.IProps.StaticProps;
import com.viacom.test.alias.maintest.pageobjects.LoginPage.LoginPage;
import com.viacom.test.alias.uitests.pageobjects.home.HomePage;
import com.viacom.test.alias.uitests.pageobjects.myprefrences.search.AssetLayoutPage;
import com.viacom.test.alias.uitests.pageobjects.profile.ProfilePage;
import com.viacom.test.alias.uitests.support.DataProviderManager;

public class VerifySearchProfileTest extends BaseTest {

	LoginPage login = new LoginPage();
	HomePage aliasHome = new HomePage();
	ProfilePage profile = new ProfilePage();
	AssetLayoutPage searchPage = new AssetLayoutPage();

	@Factory(dataProvider = StaticProps.DATA_PROVIDER, dataProviderClass = DataProviderManager.class)
	public VerifySearchProfileTest(String runParams) {
		super.setRunParams(runParams);
	}

	@Test(priority = 1)
	public void verifySearchLayoutChangeTest() throws IOException, AWTException, InterruptedException {
		openURL();
		login.loginToApplication(ConfigProps.ALIAS_USERNAME, ConfigProps.ALIAS_LOGIN_USERNAME,
				ConfigProps.ALIAS_LOGIN_PASSWORD);
		login.clickAlertPresent();
		aliasHome.clickUserNameSection();
		searchPage.selectValue();
	}
}