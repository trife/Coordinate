package org.wheatgenetics.coordinate;

/**
 * Uses:
 * android.app.Activity
 * android.content.Intent
 * android.content.pm.PackageInfo
 * android.content.pm.PackageManager.NameNotFoundException
 * android.os.Bundle
 * android.view.Menu
 * android.view.MenuItem
 * android.view.View
 * android.view.View.OnClickListener
 * android.widget.AdapterView<>
 * android.widget.AdapterView.OnItemClickListener
 * android.widget.ArrayAdapter
 * android.widget.ListView
 *
 * androidx.annotation.IntRange
 * androidx.annotation.NonNull
 * androidx.annotation.Nullable
 * androidx.appcompat.app.AppCompatActivity
 *
 * org.wheatgenetics.javalib.Utils
 *
 * org.wheatgenetics.androidlibrary.Utils
 * org.wheatgenetics.changelog.ChangeLogAlertDialog
 * org.wheatgenetics.sharedpreferences.SharedPreferences
 *
 * org.wheatgenetics.coordinate.AboutAlertDialog
 * org.wheatgenetics.coordinate.R
 * org.wheatgenetics.coordinate.Types
 *
 * org.wheatgenetics.coordinate.database.TemplatesTable
 *
 * org.wheatgenetics.coordinate.gc.StatelessGridCreator
 *
 * org.wheatgenetics.coordinate.grids.GridsActivity
 *
 * org.wheatgenetics.coordinate.model.TemplateModel
 *
 * org.wheatgenetics.coordinate.pc.ProjectCreator
 *
 * org.wheatgenetics.coordinate.preference.PreferenceActivity
 *
 * org.wheatgenetics.coordinate.projects.ProjectsActivity
 *
 * org.wheatgenetics.coordinate.templates.TemplatesActivity
 *
 * org.wheatgenetics.coordinate.tc.TemplateCreator
 * org.wheatgenetics.coordinate.tc.TemplateCreator.Handler
 */
public class MainActivity extends androidx.appcompat.app.AppCompatActivity
implements org.wheatgenetics.coordinate.tc.TemplateCreator.Handler
{
    // region Fields
    private org.wheatgenetics.coordinate.AboutAlertDialog aboutAlertDialogInstance = null;     // ll
    private java.lang.String                              versionName                    ;

    private org.wheatgenetics.changelog.ChangeLogAlertDialog changeLogAlertDialog = null;   // lazy
                                                                                            //  load
    private org.wheatgenetics.coordinate.gc.StatelessGridCreator
        statelessGridCreatorInstance = null;                                            // lazy load

    // region Create Template Fields
    private org.wheatgenetics.coordinate.database.TemplatesTable templatesTableInstance  = null;//ll
    private org.wheatgenetics.coordinate.tc.TemplateCreator      templateCreatorInstance = null;//ll
    // endregion

    private org.wheatgenetics.coordinate.pc.ProjectCreator projectCreatorInstance = null;   // lazy
    // endregion                                                                            //  load

    // region Private Methods
    private void reloadIfNecessary() { /* TODO */ }

    // region startActivity() Private Methods
    private void startGridsActivity()
    { this.startActivity(org.wheatgenetics.coordinate.grids.GridsActivity.intent(this)); }

    private void startTemplatesActivity()
    {
        this.startActivity(
            org.wheatgenetics.coordinate.templates.TemplatesActivity.intent(this));
    }

    private void startProjectsActivity()
    {
        this.startActivity(
            org.wheatgenetics.coordinate.projects.ProjectsActivity.intent(this));
    }

    private void startPreferenceActivity()
    {
        this.startActivityForResult(
            org.wheatgenetics.coordinate.preference.PreferenceActivity.intent(this),
            org.wheatgenetics.coordinate.Types.UNIQUENESS_CLICKED                          );
    }
    // endregion

    // region AboutAlertDialog Private Methods
    @androidx.annotation.NonNull
    private org.wheatgenetics.coordinate.AboutAlertDialog aboutAlertDialog()
    {
        if (null == this.aboutAlertDialogInstance) this.aboutAlertDialogInstance =
            new org.wheatgenetics.coordinate.AboutAlertDialog(
                this, this.versionName, new android.view.View.OnClickListener()
                {
                    @java.lang.Override public void onClick(final android.view.View view)
                    { org.wheatgenetics.coordinate.MainActivity.this.showChangeLog(); }
                });
        return this.aboutAlertDialogInstance;
    }

    private void showAboutAlertDialog() { this.aboutAlertDialog().show(); }
    // endregion

    private void showChangeLog()
    {
        if (null == this.changeLogAlertDialog)
            this.changeLogAlertDialog = new org.wheatgenetics.changelog.ChangeLogAlertDialog(
                /* activity               => */this,
                /* changeLogRawResourceId => */ org.wheatgenetics.coordinate.R.raw.changelog);
        this.changeLogAlertDialog.show();
    }

    private org.wheatgenetics.coordinate.gc.StatelessGridCreator statelessGridCreator()
    {
        if (null == this.statelessGridCreatorInstance) this.statelessGridCreatorInstance =
            new org.wheatgenetics.coordinate.gc.StatelessGridCreator(
                this, org.wheatgenetics.coordinate.Types.CREATE_GRID);
        return this.statelessGridCreatorInstance;
    }

    // region Create Template Private Methods
    @androidx.annotation.NonNull
    private org.wheatgenetics.coordinate.database.TemplatesTable templatesTable()
    {
        if (null == this.templatesTableInstance) this.templatesTableInstance =
            new org.wheatgenetics.coordinate.database.TemplatesTable(this);
        return this.templatesTableInstance;
    }

    private void showLongToast(final java.lang.String text)
    { org.wheatgenetics.androidlibrary.Utils.showLongToast(this, text); }

    @androidx.annotation.NonNull
    private org.wheatgenetics.coordinate.tc.TemplateCreator templateCreator()
    {
        if (null == this.templateCreatorInstance)
            this.templateCreatorInstance = new org.wheatgenetics.coordinate.tc.TemplateCreator(
                this, org.wheatgenetics.coordinate.Types.CREATE_TEMPLATE,this);
        return this.templateCreatorInstance;
    }
    // endregion

    private org.wheatgenetics.coordinate.pc.ProjectCreator projectCreator()
    {
        if (null == this.projectCreatorInstance) this.projectCreatorInstance =
            new org.wheatgenetics.coordinate.pc.ProjectCreator(this);
        return this.projectCreatorInstance;
    }
    // endregion

    // region Overridden Methods
    @java.lang.Override protected void onCreate(
    @androidx.annotation.Nullable final android.os.Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(org.wheatgenetics.coordinate.R.layout.activity_main);

        {
            final android.widget.ListView mainListView = this.findViewById(
                org.wheatgenetics.coordinate.R.id.mainListView);    // From layout/content_main.xml.
            if (null != mainListView)
            {
                // noinspection Convert2Diamond
                mainListView.setAdapter(new android.widget.ArrayAdapter<java.lang.String>(
                    this, org.wheatgenetics.coordinate.R.layout.main_list_item,
                    new java.lang.String[]{"Grids", "Templates",
                        "Projects", "Settings", "About"}));
                mainListView.setOnItemClickListener(
                    new android.widget.AdapterView.OnItemClickListener()
                    {
                        @java.lang.Override
                        public void onItemClick(final android.widget.AdapterView<?> parent,
                        final android.view.View view, final int position, final long id)
                        {
                            switch (position)
                            {
                                case 0: org.wheatgenetics.coordinate.MainActivity
                                    .this.startGridsActivity(); break;

                                case 1: org.wheatgenetics.coordinate.MainActivity
                                    .this.startTemplatesActivity(); break;

                                case 2: org.wheatgenetics.coordinate.MainActivity
                                    .this.startProjectsActivity(); break;

                                case 3: org.wheatgenetics.coordinate.MainActivity
                                    .this.startPreferenceActivity(); break;

                                case 4: org.wheatgenetics.coordinate.MainActivity
                                    .this.showAboutAlertDialog(); break;
                            }
                        }
                    });
            }
        }

        // region Get version.
        int versionCode;
        try
        {
            final android.content.pm.PackageInfo packageInfo =
                this.getPackageManager().getPackageInfo(   // throws android.content.pm.Package-
                    this.getPackageName(),0);           //  Manager.NameNotFoundException
            if (null == packageInfo)
            {
                versionCode      = 0;
                this.versionName = org.wheatgenetics.javalib.Utils.adjust(null);
            }
            else
            {
                versionCode      = packageInfo.versionCode;
                this.versionName = packageInfo.versionName;
            }
        }
        catch (final android.content.pm.PackageManager.NameNotFoundException e)
        {
            versionCode      = 0;
            this.versionName = org.wheatgenetics.javalib.Utils.adjust(null);
        }
        // endregion

        // region Set version.
        final org.wheatgenetics.sharedpreferences.SharedPreferences sharedPreferences =
            new org.wheatgenetics.sharedpreferences.SharedPreferences(
                this.getSharedPreferences("Settings", /* mode => */0));
        if (!sharedPreferences.updateVersionIsSet(versionCode))
            { sharedPreferences.setUpdateVersion(versionCode); this.showChangeLog(); }
        // endregion
    }

    @java.lang.Override public boolean onCreateOptionsMenu(final android.view.Menu menu)
    {
        this.getMenuInflater().inflate(org.wheatgenetics.coordinate.R.menu.menu_main, menu);
        return true;
    }

    @java.lang.Override protected void onActivityResult(final int requestCode,
    final int resultCode, final android.content.Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (android.app.Activity.RESULT_OK == resultCode && null != data)
            switch (requestCode)
            {
                case org.wheatgenetics.coordinate.Types.CREATE_GRID:
                    if (null != this.statelessGridCreatorInstance)
                        this.statelessGridCreatorInstance.setExcludedCells(data.getExtras());
                    break;

                case org.wheatgenetics.coordinate.Types.CREATE_TEMPLATE:
                    if (null != this.templateCreatorInstance)
                        this.templateCreatorInstance.setExcludedCells(data.getExtras());
                    break;

                case org.wheatgenetics.coordinate.Types.UNIQUENESS_CLICKED:
                    {
                        final boolean uniquenessPreferenceWasClicked;
                        {
                            final android.os.Bundle bundle = data.getExtras();
                            // noinspection SimplifiableConditionalExpression
                            uniquenessPreferenceWasClicked = null == bundle ?
                                false : bundle.getBoolean(
                                    org.wheatgenetics.coordinate.Types.UNIQUENESS_BUNDLE_KEY,
                                    false);
                        }
                        if (uniquenessPreferenceWasClicked) this.reloadIfNecessary();
                    }
                    break;
            }
    }

    // region org.wheatgenetics.coordinate.tc.TemplateCreator.Handler Overridden Method
    @java.lang.Override public void handleTemplateCreated(@androidx.annotation.NonNull
    final org.wheatgenetics.coordinate.model.TemplateModel templateModel)
    {
        final java.lang.String not = this.templatesTable().insert(templateModel) > 0 ? "" : " not";
        this.showLongToast(templateModel.getTitle() + not + " created");
    }
    // endregion
    // endregion

    // region MenuItem Event Handlers
    public void onGridMenuItemClick(@java.lang.SuppressWarnings({"unused"})
    final android.view.MenuItem menuItem) { this.statelessGridCreator().create(); }

    public void onTemplateMenuItemClick(@java.lang.SuppressWarnings({"unused"})
    final android.view.MenuItem menuItem) { this.templateCreator().create(); }

    public void onProjectMenuItemClick(@java.lang.SuppressWarnings({"unused"})
    final android.view.MenuItem menuItem) { this.projectCreator().create(); }
    // endregion
}