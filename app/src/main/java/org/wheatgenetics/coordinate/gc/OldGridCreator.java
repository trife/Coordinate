package org.wheatgenetics.coordinate.gc;

/**
 * Uses:
 * android.annotation.SuppressLint
 * android.app.Activity
 * android.os.Bundle
 *
 * androidx.annotation.IntRange
 * androidx.annotation.NonNull
 * androidx.annotation.Nullable
 *
 * org.wheatgenetics.coordinate.R
 * org.wheatgenetics.coordinate.SelectAlertDialog
 * org.wheatgenetics.coordinate.SelectAlertDialog.Handler
 * org.wheatgenetics.coordinate.Types.RequestCode
 * org.wheatgenetics.coordinate.Utils
 *
 * org.wheatgenetics.coordinate.database.EntriesTable
 * org.wheatgenetics.coordinate.database.GridsTable
 * org.wheatgenetics.coordinate.database.TemplatesTable
 *
 * org.wheatgenetics.coordinate.model.BaseJoinedGridModels
 * org.wheatgenetics.coordinate.model.Cells
 * org.wheatgenetics.coordinate.model.Cells.AmountIsTooLarge
 * org.wheatgenetics.coordinate.model.JoinedGridModel
 * org.wheatgenetics.coordinate.model.Model
 * org.wheatgenetics.coordinate.model.ProjectModel
 * org.wheatgenetics.coordinate.model.TemplateModel
 * org.wheatgenetics.coordinate.model.TemplateModels
 *
 * org.wheatgenetics.coordinate.optionalField.CheckedOptionalFields
 * org.wheatgenetics.coordinate.optionalField.NonNullOptionalFields
 *
 * org.wheatgenetics.coordinate.pc.ProjectCreator
 * org.wheatgenetics.coordinate.pc.ProjectCreator.Handler
 *
 * org.wheatgenetics.coordinate.tc.TemplateCreator
 * org.wheatgenetics.coordinate.tc.TemplateCreator.Handler
 *
 * org.wheatgenetics.coordinate.gc.SetOptionalFieldValuesAlertDialog
 * org.wheatgenetics.coordinate.gc.SetOptionalFieldValuesAlertDialog.Handler
 * org.wheatgenetics.coordinate.gc.TemplateChoiceAlertDialog
 * org.wheatgenetics.coordinate.gc.TemplateChoiceAlertDialog.Handler
 */
@java.lang.SuppressWarnings({"ClassExplicitlyExtendsObject"})
public class OldGridCreator extends java.lang.Object implements
org.wheatgenetics.coordinate.gc.TemplateChoiceAlertDialog.Handler,
org.wheatgenetics.coordinate.tc.TemplateCreator.Handler          ,
org.wheatgenetics.coordinate.gc.SetOptionalFieldValuesAlertDialog.Handler
{
    @java.lang.SuppressWarnings({"UnnecessaryInterfaceModifier"}) public interface Handler
    {
        public abstract void handleGridCreated(@androidx.annotation.IntRange(from = 1) long gridId);

        public abstract void loadProjectModel(
            @androidx.annotation.IntRange(from = 1) long projectId);
        public abstract void clearProjectModel();
    }

    // region Fields
    // ll == lazy load
                                                    private final android.app.Activity activity   ;
    @org.wheatgenetics.coordinate.Types.RequestCode private final int                  requestCode;
    @androidx.annotation.NonNull                    private final
        org.wheatgenetics.coordinate.gc.OldGridCreator.Handler handler;

    private org.wheatgenetics.coordinate.tc.TemplateCreator templateCreator = null;     // lazy load

    private org.wheatgenetics.coordinate.SelectAlertDialog    chooseOldAlertDialog = null;     // ll
    private org.wheatgenetics.coordinate.model.TemplateModels templateModels       = null;

    private org.wheatgenetics.coordinate.gc.TemplateChoiceAlertDialog
        templateChoiceAlertDialog = null;                                               // lazy load

    @androidx.annotation.Nullable private org.wheatgenetics.coordinate.model.ProjectModel
        projectModel = null;
    private org.wheatgenetics.coordinate.SelectAlertDialog getProjectChoiceAlertDialog = null; // ll

    /** 0 means no projectId. */
    @androidx.annotation.IntRange(from = 0) private long projectId;

    private org.wheatgenetics.coordinate.pc.ProjectCreator       projectCreatorInstance = null;// ll
    private org.wheatgenetics.coordinate.database.GridsTable     gridsTableInstance     = null;// ll
    private org.wheatgenetics.coordinate.database.TemplatesTable templatesTableInstance = null;// ll
    private org.wheatgenetics.coordinate.model.TemplateModel     templateModel                ;
    private java.lang.String                                     person                       ;
    private org.wheatgenetics.coordinate.optionalField.NonNullOptionalFields  optionalFields;
    private org.wheatgenetics.coordinate.gc.SetOptionalFieldValuesAlertDialog
        setOptionalFieldValuesAlertDialog = null;                                       // lazy load
    private org.wheatgenetics.coordinate.database.EntriesTable entriesTableInstance = null;    // ll
    // endregion

    // region Private Methods
    private void getTemplateChoice()
    {
        if (null == this.templateChoiceAlertDialog) this.templateChoiceAlertDialog =
            new org.wheatgenetics.coordinate.gc.TemplateChoiceAlertDialog(
                this.activity,this);
        this.templateChoiceAlertDialog.show();
    }

    // region projectCreator() Private Methods
    private void handleCreateProjectDone(
    @androidx.annotation.IntRange(from = 1) final long projectId)
    {
        this.projectId = projectId; this.handler.loadProjectModel(projectId);
        this.getTemplateChoice();
    }

    @androidx.annotation.NonNull
    private org.wheatgenetics.coordinate.pc.ProjectCreator projectCreator()
    {
        if (null == this.projectCreatorInstance) this.projectCreatorInstance =
            new org.wheatgenetics.coordinate.pc.ProjectCreator(this.activity,
                new org.wheatgenetics.coordinate.pc.ProjectCreator.Handler()
                {
                    @java.lang.Override public void handleCreateProjectDone(
                        @androidx.annotation.IntRange(from = 1) final long projectId)
                    {
                        org.wheatgenetics.coordinate.gc.OldGridCreator
                            .this.handleCreateProjectDone(projectId);
                    }
                });
        return this.projectCreatorInstance;
    }
    // endregion

    // region *Table() Lazy Load Private Methods
    @androidx.annotation.NonNull
    private org.wheatgenetics.coordinate.database.GridsTable gridsTable()
    {
        if (null == this.gridsTableInstance) this.gridsTableInstance =
            new org.wheatgenetics.coordinate.database.GridsTable(this.activity);
        return this.gridsTableInstance;
    }

    @androidx.annotation.NonNull
    private org.wheatgenetics.coordinate.database.TemplatesTable templatesTable()
    {
        if (null == this.templatesTableInstance) this.templatesTableInstance =
            new org.wheatgenetics.coordinate.database.TemplatesTable(this.activity);
        return this.templatesTableInstance;
    }

    @androidx.annotation.NonNull
    private org.wheatgenetics.coordinate.database.EntriesTable entriesTable()
    {
        if (null == this.entriesTableInstance) this.entriesTableInstance =
            new org.wheatgenetics.coordinate.database.EntriesTable(this.activity);
        return this.entriesTableInstance;
    }
    // endregion

    private void clearPerson() { this.setPerson(null); }

    /**
     * This method's mission is to 1) set this.optionalFields and 2) pass control to
     * this.handleSetValuesDone().
     */
    private void setValues()
    {
        this.clearPerson();

        if (null != this.templateModel)
            if (this.templateModel.optionalFieldsIsEmpty())
                { this.optionalFields = null; this.handleSetValuesDone(); }
            else
            {
                this.optionalFields = this.templateModel.optionalFieldsClone();
                if (null == this.setOptionalFieldValuesAlertDialog)
                    this.setOptionalFieldValuesAlertDialog =
                        new org.wheatgenetics.coordinate.gc.SetOptionalFieldValuesAlertDialog(
                            this.activity,this);
                this.setOptionalFieldValuesAlertDialog.show(this.templateModel.getTitle(),
                    new org.wheatgenetics.coordinate.optionalField.CheckedOptionalFields(
                        this.optionalFields),
                    /* firstCannotBeEmpty => */ this.templateModel.isDefaultTemplate());
            }
    }

    private boolean setTemplateFromOtherGrids()
    {
        final boolean success;
        {
            final org.wheatgenetics.coordinate.model.BaseJoinedGridModels baseJoinedGridModels =
                this.gridsTable().loadByProjectId(this.projectId);
            if (null != baseJoinedGridModels && baseJoinedGridModels.size() > 0)
            {
                final org.wheatgenetics.coordinate.model.JoinedGridModel joinedGridModel =
                    baseJoinedGridModels.get(0);
                if (null == joinedGridModel)
                    success = false;
                else
                {
                    this.templateModel =
                        this.templatesTable().get(joinedGridModel.getTemplateId());
                    this.setValues();
                    success = true;
                }
            }
            else success = false;
        }
        return success;
    }

    /**
     * This method's mission is to 1) set this.projectId, 2) sometimes cause a side effect, 3) set
     * this.templateModel and pass control to setValues() (if able to set this.templateModel) or
     * 4) pass control to this.getTemplateChoice() (if not able to set this.templateModel).
     *
     * which               item
     * ===== =================================
     *   0   Don't put this grid in a project.
     *   1   Create then select new project.
     *   2   Put this grid in "X".
     *
     * projectModel which             side effect
     * ============ ===== ===================================
     *   == null      0   none
     *   == null      1   load new project
     *   != null      0   clear old project
     *   != null      1   clear old project, load new project
     *   != null      2   none
     */
    @android.annotation.SuppressLint({"Range"}) private void handleProjectChoice(
    @androidx.annotation.IntRange(from = 0, to = 2) final int which)
    {
        if (null == this.projectModel)
            switch (which)
            {
                case 0 : this.projectId = 0                     ; break ;
                case 1 : this.projectCreator().createAndReturn(); return;
                default: throw new java.lang.IllegalArgumentException() ;
            }
        else
            switch (which)
            {
                case 0: this.projectId = 0; this.handler.clearProjectModel(); break ;
                case 1: this.projectCreator().createAndReturn();              return;

                case 2:
                    this.projectId = this.projectModel.getId();           // SuppressLint({"Range"})
                    if (this.setTemplateFromOtherGrids()) return; else break;

                default: throw new java.lang.IllegalArgumentException();
            }
        this.getTemplateChoice();
    }

    private void chooseOldAfterSelect(final int which)
    {
        if (null != this.templateModels)
        {
            this.templateModel  = this.templateModels.get(which);
            this.templateModels = null;
            this.setValues();
        }
    }
    // endregion

    public OldGridCreator(                          final android.app.Activity activity   ,
    @org.wheatgenetics.coordinate.Types.RequestCode final int                  requestCode,
    @androidx.annotation.NonNull                    final
        org.wheatgenetics.coordinate.gc.OldGridCreator.Handler handler)
    { super(); this.activity = activity; this.requestCode = requestCode; this.handler = handler; }

    // region Overridden Methods
    // region org.wheatgenetics.coordinate.gc.SetOptionalFieldValuesAlertDialog.Handler Overridden Methods
    @java.lang.Override public void setPerson(final java.lang.String person)
    { this.person = person; }

    @java.lang.Override public void handleSetValuesDone()
    {
        final org.wheatgenetics.coordinate.model.JoinedGridModel joinedGridModel =
            new org.wheatgenetics.coordinate.model.JoinedGridModel(
                /* projectId      => */ this.projectId     ,
                /* person         => */ this.person        ,
                /* optionalFields => */ this.optionalFields,
                /* templateModel  => */ this.templateModel );
        this.clearPerson(); this.optionalFields = null; this.templateModel = null;

        final org.wheatgenetics.coordinate.database.GridsTable gridsTable = this.gridsTable();
        final long gridId = gridsTable.insert(joinedGridModel);
        if (org.wheatgenetics.coordinate.model.Model.illegal(gridId))
            org.wheatgenetics.coordinate.Utils.alert(this.activity,
                org.wheatgenetics.coordinate.R.string.GridCreatorGridAlertMessage);
        else
        {
            joinedGridModel.setId(gridId);

            try
            {
                if (org.wheatgenetics.coordinate.model.Model.illegal(this.projectId))  // no project
                    joinedGridModel.makeEntryModels();                    // throws AmountIsTooLarge
                else
                {
                    final org.wheatgenetics.coordinate.model.BaseJoinedGridModels
                        baseJoinedGridModels = gridsTable.loadByProjectId(this.projectId);
                    if (null == baseJoinedGridModels)
                        joinedGridModel.makeEntryModels();                // throws AmountIsTooLarge
                    else
                        if (baseJoinedGridModels.size() <= 1)        // includes the just added grid
                            joinedGridModel.makeEntryModels();            // throws AmountIsTooLarge
                        else
                        {
                            final org.wheatgenetics.coordinate.model.Cells excludedCells =
                                baseJoinedGridModels.excludedCells(
                                    joinedGridModel.getRows(), joinedGridModel.getCols());
                            if (null == excludedCells)
                                joinedGridModel.makeEntryModels();
                            else
                                try
                                {
                                    joinedGridModel.makeEntryModels(excludedCells);    // throws
                                }                                                      //  AmountIs-
                                catch (                                                //  TooLarge
                                final org.wheatgenetics.coordinate.model.Cells.AmountIsTooLarge e)
                                { joinedGridModel.makeEntryModels() /* throws AmountIsTooLarge */; }
                        }
                }
            }
            catch (final org.wheatgenetics.coordinate.model.Cells.AmountIsTooLarge e)
            {
                gridsTable.delete(gridId);
                org.wheatgenetics.coordinate.Utils.alert(this.activity                  ,
                    org.wheatgenetics.coordinate.R.string.GridCreatorEntriesAlertMessage,
                    e.getMessage()                                                      );
                return;
            }

            if (joinedGridModel.activeRowAndOrActiveColWasAdjusted(
            org.wheatgenetics.coordinate.Utils.getAdvancement(this.activity)))
                gridsTable.update(joinedGridModel);                  // Update activeRow, activeCol.

            this.entriesTable().insert(joinedGridModel.getEntryModels());

            this.handler.handleGridCreated(gridId);
        }
    }
    // endregion

    // region org.wheatgenetics.coordinate.tc.TemplateCreator.Handler Overridden Method
    @java.lang.Override public void handleTemplateCreated(@androidx.annotation.NonNull
    final org.wheatgenetics.coordinate.model.TemplateModel templateModel)
    {
        this.templateModel = templateModel;

        final boolean inserted;
        {
            final long templateId = this.templatesTable().insert(this.templateModel);
            if (templateId > 0)
                { this.templateModel.setId(templateId); inserted = true; }
            else
            {
                org.wheatgenetics.coordinate.Utils.alert(this.activity,
                    org.wheatgenetics.coordinate.R.string.GridCreatorTemplateAlertMessage);
                inserted = false;
            }
        }
        if (inserted) this.setValues();
    }
    // endregion

    // region org.wheatgenetics.coordinate.gc.TemplateChoiceAlertDialog.Handler Overridden Methods
    @java.lang.Override public void chooseOld()
    {
        if (null == this.chooseOldAlertDialog) this.chooseOldAlertDialog =
            new org.wheatgenetics.coordinate.SelectAlertDialog(this.activity,
                new org.wheatgenetics.coordinate.SelectAlertDialog.Handler()
                {
                    @java.lang.Override public void select(final int which)
                    {
                        org.wheatgenetics.coordinate.gc.OldGridCreator.this.chooseOldAfterSelect(
                            which);
                    }
                });
        this.templateModels = this.templatesTable().load();
        if (null != this.templateModels) this.chooseOldAlertDialog.show(
            org.wheatgenetics.coordinate.R.string.GridCreatorChooseOldAlertDialogTitle,
            this.templateModels.titles()                                              );
    }

    @java.lang.Override public void chooseNew()
    {
        if (null == this.templateCreator) this.templateCreator =
            new org.wheatgenetics.coordinate.tc.TemplateCreator(
                this.activity, this.requestCode,this);
        this.templateCreator.create();
    }
    // endregion
    // endregion

    // region Public Methods
    /**
     * This method's mission is to 1) set this.projectModel and 2) pass control to
     * this.getProjectChoiceAlertDialog.
     */
    public void create(@androidx.annotation.Nullable
    final org.wheatgenetics.coordinate.model.ProjectModel projectModel)
    {
        this.projectModel = projectModel;

        if (null == this.getProjectChoiceAlertDialog) this.getProjectChoiceAlertDialog =
            new org.wheatgenetics.coordinate.SelectAlertDialog(this.activity,
                new org.wheatgenetics.coordinate.SelectAlertDialog.Handler()
                {
                    @java.lang.Override public void select(final int which)
                    {
                        org.wheatgenetics.coordinate.gc.OldGridCreator.this.handleProjectChoice(
                            which);
                    }
                });

        // noinspection CStyleArrayDeclaration
        final java.lang.String items[];
        {
            final java.lang.String
                firstItem  = "Don't add this grid to a project.",
                secondItem = "Create a project for this grid."  ;
            if (null == this.projectModel)
                items = new java.lang.String[]{firstItem, secondItem};
            else
                items = new java.lang.String[]{firstItem, secondItem,
                    "Add this grid to \"" + this.projectModel.getTitle() + "\" project."};
        }
        this.getProjectChoiceAlertDialog.show(
            org.wheatgenetics.coordinate.R.string.GridCreatorGetProjectChoiceAlertDialogTitle,
            items                                                                            );
    }

    public void setExcludedCells(final android.os.Bundle bundle)
    { if (null != this.templateCreator) this.templateCreator.setExcludedCells(bundle); }
    // endregion
}