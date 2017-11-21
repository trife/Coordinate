package org.wheatgenetics.coordinate;

/**
 * Uses:
 * android.os.Bundle
 * android.support.annotation.NonNull
 * android.support.design.widget.NavigationView
 * android.support.v4.view.GravityCompat
 * android.support.v4.widget.DrawerLayout
 * android.support.v7.app.ActionBar
 * android.support.v7.app.ActionBarDrawerToggle
 * android.support.v7.app.AppCompatActivity
 * android.support.v7.widget.Toolbar
 * android.view.Menu
 * android.view.MenuItem
 * android.view.View
 *
 * org.wheatgenetics.coordinate.R
 * org.wheatgenetics.coordinate.TestAlertDialog
 */
public class MainActivity extends android.support.v7.app.AppCompatActivity
implements android.support.design.widget.NavigationView.OnNavigationItemSelectedListener
{
    // region Fields
    private android.support.v4.widget.DrawerLayout       drawerLayout    = null;
    private org.wheatgenetics.coordinate.TestAlertDialog testAlertDialog = null;
    // endregion

    // region Overridden Methods
    @java.lang.Override
    protected void onCreate(final android.os.Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(org.wheatgenetics.coordinate.R.layout.activity_main);

        this.drawerLayout = (android.support.v4.widget.DrawerLayout) this.findViewById(
            org.wheatgenetics.coordinate.R.id.drawer_layout);      // From layout/activity_main.xml.

        // region Configure action bar.
        {
            final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)
                this.findViewById(org.wheatgenetics.coordinate.R.id.toolbar);   // From layout/app_-
            this.setSupportActionBar(toolbar);                                  //  bar_main.xml.

            {
                final android.support.v7.app.ActionBar supportActionBar =
                    this.getSupportActionBar();
                if (null != supportActionBar) supportActionBar.setTitle(null);
            }

            final android.support.v7.app.ActionBarDrawerToggle toggle =
                new android.support.v7.app.ActionBarDrawerToggle(this, this.drawerLayout, toolbar,
                    org.wheatgenetics.coordinate.R.string.navigation_drawer_open ,
                    org.wheatgenetics.coordinate.R.string.navigation_drawer_close);
            assert null != this.drawerLayout; this.drawerLayout.setDrawerListener(toggle);
            toggle.syncState();
        }
        // endregion

        // region Configure navigation menu.
        final android.support.design.widget.NavigationView navigationView =
            (android.support.design.widget.NavigationView) this.findViewById(
                org.wheatgenetics.coordinate.R.id.nav_view);       // From layout/activity_main.xml.
        assert null != navigationView; navigationView.setNavigationItemSelectedListener(this);
        // endregion
    }

    @java.lang.Override
    public void onBackPressed()
    {
        assert null != this.drawerLayout;
        if (this.drawerLayout.isDrawerOpen(android.support.v4.view.GravityCompat.START))
            this.drawerLayout.closeDrawer(android.support.v4.view.GravityCompat.START);
        else
            super.onBackPressed();
    }

    @java.lang.Override
    public boolean onCreateOptionsMenu(final android.view.Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(org.wheatgenetics.coordinate.R.menu.main, menu);
        return true;
    }

    @java.lang.Override
    public boolean onOptionsItemSelected(final android.view.MenuItem item)
    {
        // Handle action bar item clicks here.  The action bar will automatically handle clicks on
        // the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        assert null != item; final int itemId = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (org.wheatgenetics.coordinate.R.id.action_settings == itemId)
            return true;
        else
            return super.onOptionsItemSelected(item);
    }

    @java.lang.Override
    public boolean onNavigationItemSelected(
    @android.support.annotation.NonNull final android.view.MenuItem item)
    {
        // Handle navigation view item clicks here.
        switch (item.getItemId())
        {
            // The following six ids that have names that start with "nav_" come from
            // menu/activity_main_drawer.xml.
            case org.wheatgenetics.coordinate.R.id.nav_create_grid    : break;
            case org.wheatgenetics.coordinate.R.id.nav_delete_grid    : break;
            case org.wheatgenetics.coordinate.R.id.nav_create_template: break;
            case org.wheatgenetics.coordinate.R.id.nav_load_template  : break;
            case org.wheatgenetics.coordinate.R.id.nav_delete_template: break;
            case org.wheatgenetics.coordinate.R.id.nav_import_grid    : break;
            case org.wheatgenetics.coordinate.R.id.nav_export_grid    : break;
            case org.wheatgenetics.coordinate.R.id.nav_show_about     : break;
        }

        assert null != this.drawerLayout;
        this.drawerLayout.closeDrawer(android.support.v4.view.GravityCompat.START);
        return true;
    }
    // endregion

    public void onTestCoordinateButtonClick(final android.view.View view)
    {
        if (null == this.testAlertDialog) this.testAlertDialog = new TestAlertDialog(this);
        this.testAlertDialog.show();
    }
}