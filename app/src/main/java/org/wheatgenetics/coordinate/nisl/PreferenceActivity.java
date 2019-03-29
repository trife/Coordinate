package org.wheatgenetics.coordinate.nisl;

/**
 * Uses:
 * android.app.Activity
 * android.content.Intent
 * android.R
 * android.os.Bundle
 * android.preference.Preference
 * android.preference.Preference.OnPreferenceClickListener
 * android.support.annotation.Nullable
 * android.support.v7.app.ActionBar
 * android.support.v7.app.AppCompatActivity
 * android.view.MenuItem
 *
 * org.wheatgenetics.androidlibrary.Utils
 *
 * org.wheatgenetics.coordinate.Types
 *
 * org.wheatgenetics.coordinate.nisl.PreferenceFragment
 */
public class PreferenceActivity extends android.support.v7.app.AppCompatActivity
implements android.preference.Preference.OnPreferenceClickListener
{
    private boolean uniquenessPreferenceWasClicked;

    // region Overridden Methods
    @java.lang.Override protected void onCreate(
    @android.support.annotation.Nullable final android.os.Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (null == savedInstanceState)
            this.uniquenessPreferenceWasClicked = false;
        else
            this.uniquenessPreferenceWasClicked = savedInstanceState.getBoolean(
                org.wheatgenetics.coordinate.Types.UNIQUENESS_BUNDLE_KEY,false);

        {
            final android.support.v7.app.ActionBar supportActionBar =
                this.getSupportActionBar();
            if (null != supportActionBar)
            {
                supportActionBar.setTitle                 (/* title        => */ null);
                supportActionBar.setDisplayHomeAsUpEnabled(/* showHomeAsUp => */ true);
            }
        }

        // Display PreferenceFragment as the main content.
        this.getFragmentManager().beginTransaction().replace(android.R.id.content,
            new org.wheatgenetics.coordinate.nisl.PreferenceFragment()).commit();
    }

    @java.lang.Override protected void onStart()
    {
        super.onStart();
        org.wheatgenetics.androidlibrary.Utils.showLongToast(
            this,"Press \"Back\" when done.");
    }

    @java.lang.Override protected void onSaveInstanceState(final android.os.Bundle outState)
    {
        if (null != outState) outState.putBoolean(
            org.wheatgenetics.coordinate.Types.UNIQUENESS_BUNDLE_KEY,
            this.uniquenessPreferenceWasClicked                     );
        super.onSaveInstanceState(outState);
    }

    @java.lang.Override public boolean onOptionsItemSelected(final android.view.MenuItem item)
    {
        {
            final android.content.Intent intent = new android.content.Intent();
            {
                final android.os.Bundle bundle = new android.os.Bundle();
                bundle.putBoolean(
                    org.wheatgenetics.coordinate.Types.UNIQUENESS_BUNDLE_KEY,
                    this.uniquenessPreferenceWasClicked                     );
                intent.putExtras(bundle);
            }
            this.setResult(android.app.Activity.RESULT_OK, intent);
        }
        this.finish(); return super.onOptionsItemSelected(item);
    }

    // region android.preference.Preference.OnPreferenceClickListener Overridden Method
    @java.lang.Override
    public boolean onPreferenceClick(final android.preference.Preference preference)
    { this.uniquenessPreferenceWasClicked = true; return true; }
    // endregion
    // endregion
}