package com.viacom.test.alias.uitests.tests.userpreferences.profile;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.viacom.test.alias.common.BaseTest;
import com.viacom.test.alias.common.constants.CartConstants;
import com.viacom.test.alias.common.constants.ProfileConstants;
import com.viacom.test.alias.common.constants.SearchConstants;
import com.viacom.test.alias.common.util.props.IProps.ConfigProps;
import com.viacom.test.alias.common.util.props.IProps.StaticProps;
import com.viacom.test.alias.maintest.pageobjects.LoginPage.LoginPage;
import com.viacom.test.alias.maintest.pageobjects.search.SearchPage;
import com.viacom.test.alias.uitests.pageobjects.cartpage.CartPage;
import com.viacom.test.alias.uitests.pageobjects.home.HomePage;
import com.viacom.test.alias.uitests.pageobjects.myprefrences.search.AssetLayoutPage;
import com.viacom.test.alias.uitests.pageobjects.profile.ProfilePage;
import com.viacom.test.alias.uitests.pageobjects.search.AssetSearchPage;
import com.viacom.test.alias.uitests.pageobjects.search.ClipSearchPage;
import com.viacom.test.alias.uitests.pageobjects.search.ContentSearchPage;
import com.viacom.test.alias.uitests.pageobjects.search.PhysicalWorkOrderPage;
import com.viacom.test.alias.uitests.support.DataProviderManager;

public class VerifyLoginandAddtoCartTest extends BaseTest {

	LoginPage login = new LoginPage();
	HomePage aliasHome = new HomePage();
	SearchPage search = new SearchPage();
	CartPage cart = new CartPage();
	AssetSearchPage searchpage = new AssetSearchPage();
	ClipSearchPage clippage = new ClipSearchPage();
	ContentSearchPage contentpage = new ContentSearchPage();
	AssetLayoutPage assetlaypage = new AssetLayoutPage();
	PhysicalWorkOrderPage physicalorderpage = new PhysicalWorkOrderPage();
	ProfilePage profile = new ProfilePage();
	public int count ;
	
	@Factory(dataProvider = StaticProps.DATA_PROVIDER, dataProviderClass = DataProviderManager.class)
	public VerifyLoginandAddtoCartTest(String runParams) {
		super.setRunParams(runParams);		
	}
	
	@Test(priority = 1)
	public void verifyAddtoCartandApplyPhysicalOrderTest() throws IOException, AWTException, InterruptedException  {	
		openURL();
		login.loginToApplication(ConfigProps.ALIAS_USERNAME, ConfigProps.ALIAS_LOGIN_USERNAME, ConfigProps.ALIAS_LOGIN_PASSWORD);
		login.clickAlertPresent();
		aliasHome.clickUserNameSection();
		assetlaypage.selectValue( "Barcode", "Content Owner","DSID");
		aliasHome.enterSearchItem(SearchConstants.BARCODE_5.value());;
		aliasHome.clickSearch();
		searchpage.clickAssetItemCheckbox(SearchConstants.BARCODE_5.value());
		count = searchpage.getCartCount();
		searchpage.AddtoCart();		
		searchpage.verifyMessageIsDisplayed(searchpage.itemaddedtocartmessage(),CartConstants.ITEM_ADDED_TO_CART_MESSAGE.value());
		searchpage.verifyCartCount(count,1);
		searchpage.clickOnCartIcon();
		searchpage.assertItemVisibleInTheCartTable(SearchConstants.BARCODE_5.value());
		searchpage.verifyMessageIsDisplayed(cart.carttitle(),CartConstants.CART_TITLE.value());
		cart.clickItemCheckboxOnCartPage(SearchConstants.BARCODE_5.value());
		cart.clickOnRequiredOption(cart.applyparametersicon());
		physicalorderpage.rightClickTest();		
		physicalorderpage.enterCostCenterValue();
		physicalorderpage.enterSupervisorValue();
		physicalorderpage.clickApplyButton();
		searchpage.verifyMessageIsDisplayed(cart.workordersuccessmessage(),CartConstants.WORKORDER_APPLIED_MESSAGE.value());
		cart.clickRemoveItemFromCartIcon();
		searchpage.verifyMessageIsDisplayed(cart.confirmdeletemessage(),CartConstants.CONFIRM_DELETE_MESSAGE.value());
		cart.clickYesOnModalPageCart();
		searchpage.verifyMessageIsDisplayed(cart.itemremovedmessage(),CartConstants.ITEM_REMOVED_FROM_CART_MESSAGE.value());
	}
}