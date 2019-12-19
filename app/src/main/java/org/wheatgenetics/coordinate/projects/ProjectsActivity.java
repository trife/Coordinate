package org.wheatgenetics.coordinate.projects;

/**
 * Uses:
 * android.content.pm.PackageManager
 * android.Manifest.permission
 * android.os.Bundle
 * android.view.Menu
 * android.view.MenuItem
 * android.view.View
 * android.widget.AdapterView<>
 * android.widget.AdapterView.OnItemClickListener
 * android.widget.ListView
 *
 * androidx.annotation.IntRange
 * androidx.annotation.NonNull
 * androidx.annotation.Nullable
 * androidx.appcompat.app.AppCompatActivity
 *
 * org.wheatgenetics.coordinate.R
 *
 * org.wheatgenetics.coordinate.pc.ProjectCreator
 * org.wheatgenetics.coordinate.pc.ProjectCreator.Handler
 *
 * org.wheatgenetics.coordinate.pe.ProjectExporter
 *
 * org.wheatgenetics.coordinate.projects.ProjectsAdapter
 * org.wheatgenetics.coordinate.projects.ProjectClickAlertDialog
 * org.wheatgenetics.coordinate.projects.ProjectClickAlertDialog.Handler
 */
public class ProjectsActivity extends androidx.appcompat.app.AppCompatActivity
{
    private static final int EXPORT_PROJECT_REQUEST_CODE = 10;

    // region Fields
    private org.wheatgenetics.coordinate.projects.ProjectsAdapter projectsAdapter = null;

    // region exportProject() Fields
    @androidx.annotation.IntRange(from = 1) private long             projectId    ;
                                            private java.lang.String directoryName;

    private org.wheatgenetics.coordinate.pe.ProjectExporter projectExporterInstance = null;    // ll
    // endregion

    private org.wheatgenetics.coordinate.projects.ProjectClickAlertDialog
        projectClickAlertDialogInstance = null;                                         // lazy load
    private org.wheatgenetics.coordinate.pc.ProjectCreator projectCreator = null;       // lazy load
    // endregion

    // region Private Methods
    private void notifyDataSetChanged()
    { if (null != this.projectsAdapter) this.projectsAdapter.notifyDataSetChanged(); }

    // region exportProject() Private Methods
    @androidx.annotation.NonNull
    private org.wheatgenetics.coordinate.pe.ProjectExporter projectExporter()
    {
        if (null == this.projectExporterInstance) this.projectExporterInstance =
            new org.wheatgenetics.coordinate.pe.ProjectExporter(this,
                org.wheatgenetics.coordinate.projects.ProjectsActivity.EXPORT_PROJECT_REQUEST_CODE);
        return this.projectExporterInstance;
    }

    private void exportProject()
    { this.projectExporter().export(this.projectId, this.directoryName); }

    private void exportProject(@androidx.annotation.IntRange(from = 1) final long projectId,
    final java.lang.String directoryName)
    { this.projectId = projectId; this.directoryName = directoryName; this.exportProject(); }
    // endregion

    private org.wheatgenetics.coordinate.projects.ProjectClickAlertDialog projectClickAlertDialog()
    {
        if (null == this.projectClickAlertDialogInstance) this.projectClickAlertDialogInstance =
            new org.wheatgenetics.coordinate.projects.ProjectClickAlertDialog(this,
                new org.wheatgenetics.coordinate.projects.ProjectClickAlertDialog.Handler()
                {
                    @java.lang.Override public void createGrid(
                    @androidx.annotation.IntRange(from = 1) final long projectId)
                    {
                        // TODO
                    }

                    @java.lang.Override public void showGrids(
                    @androidx.annotation.IntRange(from = 1) final long projectId)
                    {
                        // TODO
                    }

                    @java.lang.Override public void respondToDeletedProject()
                    {
                        org.wheatgenetics.coordinate.projects.ProjectsActivity
                            .this.notifyDataSetChanged();
                    }

                    @java.lang.Override public void exportProject(
                    @androidx.annotation.IntRange(from = 1) final long             projectId    ,
                                                            final java.lang.String directoryName)
                    {
                        org.wheatgenetics.coordinate.projects.ProjectsActivity
                            .this.exportProject(projectId, directoryName);
                    }
                });
        return this.projectClickAlertDialogInstance;
    }

    private void showProjectClickAlertDialog(@androidx.annotation.IntRange(from = 1)
    final long projectId) { this.projectClickAlertDialog().show(projectId); }

    private void handleCreateProjectDone() { this.notifyDataSetChanged(); }
    // endregion

    // region Overridden Methods
    @java.lang.Override protected void onCreate(
    @androidx.annotation.Nullable final android.os.Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(org.wheatgenetics.coordinate.R.layout.activity_projects);

        final android.widget.ListView projectsListView = this.findViewById(
            org.wheatgenetics.coordinate.R.id.projectsListView);
        if (null != projectsListView)
        {
            projectsListView.setAdapter(this.projectsAdapter =
                new org.wheatgenetics.coordinate.projects.ProjectsAdapter(this));
            projectsListView.setOnItemClickListener(
                new android.widget.AdapterView.OnItemClickListener()
                {
                    @java.lang.Override public void onItemClick(
                    final android.widget.AdapterView<?> parent, final android.view.View view,
                    final int position, final long id)
                    {
                        org.wheatgenetics.coordinate.projects.ProjectsActivity
                            .this.showProjectClickAlertDialog(id);
                    }
                });
        }
    }

    @java.lang.Override public boolean onCreateOptionsMenu(final android.view.Menu menu)
    {
        this.getMenuInflater().inflate(org.wheatgenetics.coordinate.R.menu.menu_projects, menu);
        return true;
    }

    @java.lang.Override public void onRequestPermissionsResult(final int requestCode,
    @java.lang.SuppressWarnings({"CStyleArrayDeclaration"}) @androidx.annotation.NonNull
        final java.lang.String permissions[],
    @java.lang.SuppressWarnings({"CStyleArrayDeclaration"}) @androidx.annotation.NonNull
        final int grantResults[])
    {
        boolean permissionFound = false;
        for (final java.lang.String permission: permissions)
            if (android.Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission))
                { permissionFound = true; break; }

        if (permissionFound) for (final int grantResult: grantResults)
            if (android.content.pm.PackageManager.PERMISSION_GRANTED == grantResult)
                // noinspection SwitchStatementWithTooFewBranches
                switch (requestCode)
                {
                    case org.wheatgenetics.coordinate.projects.ProjectsActivity
                        .EXPORT_PROJECT_REQUEST_CODE: this.exportProject(); break;
                }
    }
    // endregion

    public void onNewProjectMenuItemClick(@java.lang.SuppressWarnings({"unused"})
    final android.view.MenuItem menuItem)
    {
        if (null == this.projectCreator)
            this.projectCreator = new org.wheatgenetics.coordinate.pc.ProjectCreator(this,
                new org.wheatgenetics.coordinate.pc.ProjectCreator.Handler()
                {
                    @java.lang.Override public void handleCreateProjectDone(
                    @androidx.annotation.IntRange(from = 1) final long projectId)
                    {
                        org.wheatgenetics.coordinate.projects
                            .ProjectsActivity.this.handleCreateProjectDone();
                    }
                });
        this.projectCreator.createAndReturn();
    }
}