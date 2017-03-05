package co.uk.RandomPanda30.DShops.Files;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum Items {

	EXITBUTTON_NAME ("%BExit"),

	FORSALEINV_PAPERDETAILS_NAME ("%NState: %AFor sale"),

	FORSALEINV_FORSALE_NAME ("%GPurchase door!"),
	FORSALEINV_FORSALE_LORES (new ArrayList<String>() {
		{
			add("%NThis door is for sale");
			add("%NPrice: %A'price'");
		}
	}),

	FORRENTINV_PAPERDETAILS_NAME ("%NState: %AFor rent"),

	FORRENTINV_FORRENT_NAME ("%GRent door!"),
	FORRENTINV_FORRENT_LORES (new ArrayList<String>() {
		{
			add("%NRent this door for 5 days");
			add("%NPrice: %A'price'");
		}
	}),

	RENTEDINV_PAYRENT_NAME ("%NPay your rent"),
	RENTEDINV_PAYRENT_LORES (new ArrayList<String>() {
		{
			add("%NClick here to update your");
			add("%Ntime on your rent");
			add("%NCurrent Time: 'time'");
		}
	}),

	OWNEDINV_PAPERDETAILS_NAME ("%NState: %AOwned"),

	OWNEDINV_OPENDOOR_NAME ("%GOpen door"),
	OWNEDINV_CLOSEDOOR_NAME ("%BClose door"),

	OWNEDINV_EDITFRIENDS_NAME ("%NEdit friends"),

	OWNEDINV_SELLDOOR_NAME ("%BSell door"),

	NEXTBUTTON_NAME ("%ANext page"),
	NEXTBUTTON_LORES (new ArrayList<String>() {
		{
			add("%AClick here to access the next page");
		}
	}),

	PREVIOUSBUTTON_NAME ("%ALast page"),
	PREVIOUSBUTTON_LORES (new ArrayList<String>() {
		{
			add("%AClick here to access the last page");
		}
	}),

	SINV_SBFL_NAME ("%NSearch by first letter of a name"),
	SINV_SBFL_LORES (new ArrayList<String>() {
		{
			add("%NClick here to search for a player");
			add("%Nusing the first letter of their name");
		}
	}),

	ADDBUTTON_NAME ("%G+10"),
	MINUSBUTTON_NAME ("%B-10"),

	DOORPRICE_NAME ("%NDoor price: %A'price'"),

	DOORSETTINGS_NAME ("%AEdit door options"),

	EDITFRIENDSINV_ADDFRIEND_NAME ("%GAdd %Nfriend"),
	EDITFRIENDSINV_REMOVEFRIEND_NAME ("%BRemove %Nfriend");

	public Object value;

	Items (Object value) {
		this.value = value;
	}

}
