package com.viacom.test.alias.uitests.tests.userpreferences.profile;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.viacom.test.alias.common.BaseTest;
import com.viacom.test.alias.common.constants.ProfileConstants;
import com.viacom.test.alias.common.util.props.IProps.ConfigProps;
import com.viacom.test.alias.common.util.props.IProps.StaticProps;
import com.viacom.test.alias.maintest.pageobjects.LoginPage.LoginPage;
import com.viacom.test.alias.uitests.pageobjects.home.HomePage;
import com.viacom.test.alias.uitests.pageobjects.profile.ProfilePage;
import com.viacom.test.alias.uitests.support.DataProviderManager;

public class VerifyUserProfileTest extends BaseTest {

	LoginPage login = new LoginPage();
	HomePage aliasHome = new HomePage();
	ProfilePage profile = new ProfilePage();

	@Factory(dataProvider = StaticProps.DATA_PROVIDER, dataProviderClass = DataProviderManager.class)
	public VerifyUserProfileTest(String runParams) {
		super.setRunParams(runParams);
	}

	@Test
	public void verifyErrorMessageDisplayedWithoutMandatoryTest() throws IOException, AWTException, InterruptedException {
		openURL();
		login.loginToApplication(ConfigProps.ALIAS_USERNAME, ConfigProps.ALIAS_LOGIN_USERNAME,
								 ConfigProps.ALIAS_LOGIN_PASSWORD);
		login.clickAlertPresent();
		aliasHome.clickUserNameSection();
		profile.verifyUserNameField(ProfileConstants.EXPECTED_NONEDITABLE_FIELDS.value());
		profile.verifyLastNameField(ProfileConstants.EXPECTED_NONEDITABLE_FIELDS.value());
		profile.verifyPhoneNumberField(ProfileConstants.INVALID_PHONE_NUMBER.value());
		profile.verifyEmailField(ProfileConstants.EXPECTED_NONEDITABLE_FIELDS.value());
		profile.clickSave();
		profile.verifyErrorMessageIsDisplayed(ProfileConstants.ERROR_MESSAGE.value());
	}

	@Test
	public void verifyUserProfileTest() throws IOException, AWTException, InterruptedException {
		openURL();
		login.loginToApplication(ConfigProps.ALIAS_USERNAME, ConfigProps.ALIAS_LOGIN_USERNAME,
				ConfigProps.ALIAS_LOGIN_PASSWORD);
		login.clickAlertPresent();
		aliasHome.clickUserNameSection();
		profile.verifyUserNameField(ProfileConstants.EXPECTED_NONEDITABLE_FIELDS.value());
		profile.enterCostCenterValue(ProfileConstants.COST_CENTER.value());
		profile.enterPhoneNumber(ProfileConstants.VALID_PHONE_NUMBER.value());
		profile.clickProxySlider();
		profile.clickSave();
	}
	
	
}