package com.codingspezis.android.metalonly.player;

import android.content.*;
import android.net.*;
import android.os.*;
import android.support.v4.app.*;
import android.widget.*;

import com.actionbarsherlock.app.*;
import com.actionbarsherlock.view.*;
import com.googlecode.androidannotations.annotations.*;
import com.googlecode.androidannotations.annotations.res.*;

/**
 * 
 * this activity shows information about the application e.g. codingspezis.com
 * and used software
 * 
 * @author codingspezis.com
 * 
 */
@EActivity(R.layout.activity_about)
public class AboutActivity extends SherlockActivity {

	@StringRes
	String mailaddress_codingspezis, app_name, app_version;

	@ViewById
	@FromHtml(R.string.aboutThisApp)
	TextView textAboutApp;

	@ViewById
	@FromHtml(R.string.url_opencore)
	TextView textOpencoreLink;

	@ViewById
	@FromHtml(R.string.url_aacdecoder)
	TextView textAacDecoderLink;

	@ViewById
	@FromHtml(R.string.url_sherlock)
	TextView textSherlockLink;

	@ViewById
	@FromHtml(R.string.url_lazylist)
	TextView textLazyListLink;

	@ViewById
	@FromHtml(R.string.url_androidannotations)
	TextView textAndroidannotationsLink;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	/**
	 * sends system intent ACTION_VIEW (open browser)
	 * 
	 * @param URL
	 *            browser opens this URL
	 */
	private void openWebsite(String URL) {
		Uri metalOnly = Uri.parse(URL);
		Intent homepage = new Intent(Intent.ACTION_VIEW, metalOnly);
		startActivity(homepage);
	}

	/**
	 * sends system intent ACTION_SEND (send mail)
	 * 
	 * @param strTo
	 *            receiver of mail
	 * @param strSubject
	 *            subject of mail
	 * @param strText
	 *            text of mail
	 */
	private void sendEmail(String strTo, String strSubject, String strText) {
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setType("text/plain");
		emailIntent.setType("message/rfc822");
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { strTo });
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, strSubject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, strText);
		try {
			startActivity(Intent.createChooser(emailIntent, strTo)); // TODO:
																		// change
																		// this
		} catch (android.content.ActivityNotFoundException ex) {
			MainActivity.toastMessage(this, getString(R.string.no_mail_app));
		}
	}

	@Click(R.id.buttonFeedback)
	void buttonFeedbackClicked() {
		String subject = "[" + app_name + " " + app_version
				+ "] Feedback, Fehler";
		sendEmail(mailaddress_codingspezis, subject, "");
	}

	@Click({ R.id.textOpencoreLicenseApache, R.id.textSherlockLicenseApache,
			R.id.textAndroidannotationsLicenseApache })
	void displayApacheLicense() {
		displayLicense(LicenseActivity.KEY_BU_LICENSE_APACHE);
	}

	@Click({ R.id.textLazyListLicenseMIT })
	void displayMitLicense() {
		displayLicense(LicenseActivity.KEY_BU_LICENSE_MIT);
	}

	@Click({ R.id.textAacDecoderLicenseLGPL })
	void displayLgplLicense() {
		displayLicense(LicenseActivity.KEY_BU_LICENSE_LGPL);
	}

	/**
	 * displays specified license
	 * 
	 * @param license
	 *            license to display
	 */
	private void displayLicense(String license) {
		Intent licenseIntent = new Intent(getApplicationContext(),
				LicenseActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString(LicenseActivity.KEY_BU_LICENSE_NAME, license);
		licenseIntent.putExtras(bundle);
		startActivity(licenseIntent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == com.actionbarsherlock.R.id.abs__home) {
			Intent intent = new Intent(this, MainActivity.class);
			NavUtils.navigateUpTo(this, intent);
			return true;
		}
		return false;
	}
}
