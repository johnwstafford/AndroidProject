package validation;

import android.util.SparseBooleanArray;
import android.widget.ListView;

public class Validation
{
	/**
	 * (1) Checks for null text (2) If text contains a letter, returns true
	 * 
	 * @param text
	 * @return boolean
	 */
	public static boolean checkForLetters(String text)
	{
		if (Validation.checkForNullValue(text))
		{
			char[] charArray = text.toCharArray();
			for (char c : charArray)
			{
				if (Character.isLetter(c))
				{
					return true;
				}
			}
			return false;
		}
		else
		{
			return false;
		}
	}

	private static boolean checkForNullValue(String text)
	{
		if (text != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * This checks an array of chars which represents the string input for letters or digits ( Character.isLetterOrDigit )
	 * 
	 * @param text
	 * @return boolean
	 */
	public static boolean checkStringForAlphanumeric(String text)
	{
		if (Validation.checkForNullValue(text))
		{
			for (char tempChar : text.toCharArray())
			{
				if (!Character.isLetterOrDigit(tempChar))
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * (1) Checks for null text (2) Checks that all text entered is between 0 - 9
	 * 
	 * @param text
	 * @return boolean
	 */
	public static boolean ensureTextContainsAllNumbers(String text)
	{
		if (Validation.checkForNullValue(text))
		{
			char[] charArray = text.toCharArray();
			for (char c : charArray)
			{
				if (!Character.isDigit(c))
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Returns the FIRST position of a selected item from a ListView. If none found, returns -1
	 * 
	 * @param listView
	 * @return
	 */
	public static int getListViewSelectedItem(ListView listView)
	{
		final SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

		for (int i = 0; i < checkedItems.size(); i++)
		{
			// And this tells us the item status at the above position
			final boolean isChecked = checkedItems.valueAt(i);
			if (isChecked)
			{
				// This tells us the item position we are looking at
				return checkedItems.keyAt(i);
			}
		}
		return -1;
	}

	/**
	 * (1) Checks for null text (2) If not null, returns false if the length of the text from the EditText is greater than 'lengthToCheck'
	 * 
	 * @param text
	 * @param lengthToCheck
	 * @return
	 */
	public static boolean isLengthLessThanOrEqualToInputValue(String text, int lengthToCheck)
	{
		if (Validation.checkForNullValue(text))
		{
			if (text.length() <= lengthToCheck)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	/**
	 * (1) Checks for null text (2) If not null, returns true if email is valid
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isValidEmail(String text)
	{
		if (Validation.checkForNullValue(text))
		{
			if (android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
