package org.wheatgenetics.coordinate.collector;

import android.content.Intent;
import android.media.MediaPlayer;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import org.wheatgenetics.coordinate.R;
import org.wheatgenetics.coordinate.database.EntriesTable;
import org.wheatgenetics.coordinate.database.GridsTable;
import org.wheatgenetics.coordinate.database.ProjectsTable;
import org.wheatgenetics.coordinate.griddisplay.GridDisplayFragment;
import org.wheatgenetics.coordinate.model.CheckedIncludedEntryModel;
import org.wheatgenetics.coordinate.model.CurrentGridUniqueEntryModels;
import org.wheatgenetics.coordinate.model.CurrentGridUniqueJoinedGridModel;
import org.wheatgenetics.coordinate.model.DisplayModel;
import org.wheatgenetics.coordinate.model.ElementModel;
import org.wheatgenetics.coordinate.model.EntryModel;
import org.wheatgenetics.coordinate.model.EntryModels;
import org.wheatgenetics.coordinate.model.ExcludedEntryModel;
import org.wheatgenetics.coordinate.model.IncludedEntryModel;
import org.wheatgenetics.coordinate.model.JoinedGridModel;
import org.wheatgenetics.coordinate.model.Model;
import org.wheatgenetics.coordinate.model.ProjectModel;
import org.wheatgenetics.coordinate.model.UniqueEntryModels;
import org.wheatgenetics.coordinate.optionalField.NonNullOptionalFields;
import org.wheatgenetics.zxing.BarcodeScanner;

@SuppressWarnings({"ClassExplicitlyExtendsObject"})
abstract class BaseCollector extends Object implements
        GridDisplayFragment.Handler,
        EntryModels.FilledHandler,
        DataEntryFragment.Handler {
    // region Fields
    @NonNull
    private final AppCompatActivity activity;

    private final GridDisplayFragment gridDisplayFragment;
    private final DataEntryFragment dataEntryFragment;

    @RestrictTo(RestrictTo.Scope.SUBCLASSES)
    JoinedGridModel joinedGridModel = null;

    // region Table Fields
    private ProjectsTable projectsTableInstance = null;  // ll
    private GridsTable gridsTableInstance = null;  // ll
    private EntriesTable entriesTableInstance = null;  // ll
    // endregion

    private MediaPlayer gridEndMediaPlayer = null,
            rowOrColumnEndMediaPlayer = null, disallowedDuplicateMediaPlayer = null;       // lazy loads
    private UniqueAlertDialog uniqueAlertDialog = null; // ll

    private BarcodeScanner barcodeScannerInstance = null;       // lazy load
    // endregion

    @RestrictTo(RestrictTo.Scope.SUBCLASSES)
    BaseCollector(@NonNull final AppCompatActivity activity) {
        super();

        this.activity = activity;

        @NonNull final FragmentManager fragmentManager =
                this.activity.getSupportFragmentManager();
        this.gridDisplayFragment = (GridDisplayFragment)
                fragmentManager.findFragmentById(R.id.gridDisplayFragment);
        this.dataEntryFragment = (DataEntryFragment)
                fragmentManager.findFragmentById(R.id.dataEntryFragment);
    }

    // region Private Methods
    @Nullable
    private EntriesTable entriesTable() {
        return Utils.entriesTable(
                this.entriesTableInstance, this.gridsTable(), this.activity);
    }

    // region Preference Private Methods
    private org.wheatgenetics.coordinate.preference.Utils.Direction getDirection() {
        return org.wheatgenetics.coordinate.preference.Utils.getDirection(this.activity);
    }

    private boolean getUnique() {
        return org.wheatgenetics.coordinate.preference.Utils.getUnique(this.activity);
    }
    // endregion

    private boolean getSoundsOn() {
        return org.wheatgenetics.coordinate.preference.Utils.getSoundsOn(this.activity);
    }

    private void populateDataEntryFragment() {
        if (null != this.dataEntryFragment) this.dataEntryFragment.populate();
    }

    private void goToNext(final EntryModel entryModel) {
        final GridsTable gridsTable = this.gridsTable();
        if (this.joinedGridModelIsLoaded() && null != gridsTable)
            if (this.joinedGridModel.goToNext(entryModel, this.getDirection(), this)) {
                gridsTable.update(this.joinedGridModel);             // Update activeRow, activeCol.

                if (null != this.gridDisplayFragment)
                    this.gridDisplayFragment.notifyDataSetChanged();
                this.populateDataEntryFragment();
            }
    }

    private void setEntry(final String entry) {
        if (null != this.dataEntryFragment) this.dataEntryFragment.setEntry(entry);
    }

    private void handleDuplicateCheckException(
            @NonNull final String message) {
        if (this.getSoundsOn()) {
            if (null == this.disallowedDuplicateMediaPlayer)
                this.disallowedDuplicateMediaPlayer = MediaPlayer.create(
                        this.activity, R.raw.unsure);
            this.disallowedDuplicateMediaPlayer.start();
        }

        if (null == this.uniqueAlertDialog) this.uniqueAlertDialog =
                new UniqueAlertDialog(this.activity);
        this.uniqueAlertDialog.show(message);
    }
    // endregion

    @NonNull
    private BarcodeScanner barcodeScanner() {
        if (null == this.barcodeScannerInstance) this.barcodeScannerInstance =
                new BarcodeScanner(this.activity);
        return this.barcodeScannerInstance;
    }

    // region Package Methods
    @RestrictTo(RestrictTo.Scope.SUBCLASSES)
    @Nullable
    GridsTable gridsTable() {
        return Utils.gridsTable(
                this.gridsTableInstance, this.activity);
    }
    // endregion

    @RestrictTo(RestrictTo.Scope.SUBCLASSES)
    @NonNull
    ProjectsTable projectsTable() {
        if (null == this.projectsTableInstance) this.projectsTableInstance =
                new ProjectsTable(this.activity);
        return this.projectsTableInstance;
    }

    // region Overridden Methods
    // region org.wheatgenetics.coordinate.griddisplay.GridDisplayFragment.Handler Overridden Methods
    @Override
    public DisplayModel getDisplayModel() {
        return this.joinedGridModel;
    }

    @Override
    public void toggle(@Nullable final ElementModel elementModel) {
        if (this.joinedGridModelIsLoaded()) {
            final EntriesTable entriesTable =
                    this.entriesTable();
            if (null != entriesTable) {
                final EntryModel entryModel =
                        (EntryModel) elementModel;
                if (this.joinedGridModel instanceof
                        CurrentGridUniqueJoinedGridModel) {
                    final CurrentGridUniqueJoinedGridModel
                            currentGridUniqueJoinedGridModel =
                            (CurrentGridUniqueJoinedGridModel)
                                    this.joinedGridModel;
                    try {
                        currentGridUniqueJoinedGridModel.checkThenSetEntryModel(entryModel);//throws
                    } catch (final
                    CheckedIncludedEntryModel.CheckException e) {
                        return;
                    }
                } else this.joinedGridModel.setEntryModel(entryModel);
                entriesTable.insertOrUpdate(entryModel);
                if (entryModel instanceof ExcludedEntryModel)
                    if (this.joinedGridModel.getActiveEntryModel() == entryModel)
                        this.goToNext(entryModel);
            }
        }
    }

    @Override
    public int getActiveRow() {
        return this.joinedGridModelIsLoaded() ? this.joinedGridModel.getActiveRow() : -1;
    }

    @Override
    public int getActiveCol() {
        return this.joinedGridModelIsLoaded() ? this.joinedGridModel.getActiveCol() : -1;
    }

    @Override
    public void activate(final int row, final int col) {
        final GridsTable gridsTable = this.gridsTable();
        if (null != gridsTable) {
            if (this.joinedGridModelIsLoaded())
                if (this.joinedGridModel.setActiveRowAndActiveCol(row, col))
                    gridsTable.update(this.joinedGridModel);

            this.setEntry(this.getEntryValue());
        }
    }

    @Override
    @Nullable
    public CheckedIncludedEntryModel.Checker getChecker() {
        if (this.joinedGridModel instanceof
                CurrentGridUniqueJoinedGridModel)
            return (CurrentGridUniqueEntryModels)
                    this.joinedGridModel.getEntryModels();
        else return null;
    }
    // endregion

    // region org.wheatgenetics.coordinate.model.EntryModels.FilledHandler Overridden Methods
    @Override
    public void handleFilledGrid() {
        org.wheatgenetics.coordinate.Utils.alert(this.activity,
                R.string.BaseCollectorFilledGridAlertMessage);

        if (this.getSoundsOn()) {
            if (null == this.gridEndMediaPlayer)
                this.gridEndMediaPlayer = MediaPlayer.create(
                        this.activity, R.raw.plonk);
            this.gridEndMediaPlayer.start();
        }
    }

    @Override
    public void handleFilledRowOrCol() {
        if (this.getSoundsOn()) {
            if (null == this.rowOrColumnEndMediaPlayer)
                this.rowOrColumnEndMediaPlayer = MediaPlayer.create(
                        this.activity, R.raw.row_or_column_end);
            this.rowOrColumnEndMediaPlayer.start();
        }
    }
    // endregion

    // region org.wheatgenetics.coordinate.collector.DataEntryFragment.Handler Overridden Methods
    @Override
    public String getEntryValue() {
        if (this.joinedGridModelIsLoaded()) {
            final EntryModel activeEntryModel =
                    this.joinedGridModel.getActiveEntryModel();
            return null == activeEntryModel ? null : activeEntryModel.getValue();
        } else return null;
    }

    @Override
    public String getProjectTitle() {
        if (this.joinedGridModelIsLoaded()) {
            @IntRange(from = 0) final long projectId =
                    this.joinedGridModel.getProjectId();
            final String NONE = "none";

            if (Model.illegal(projectId))
                return NONE;
            else {
                final ProjectModel projectModel =
                        this.projectsTable().get(projectId);
                return null == projectModel ? NONE : projectModel.getTitle();
            }
        } else return "";
    }

    @Override
    public String getTemplateTitle() {
        return this.joinedGridModelIsLoaded() ? this.joinedGridModel.getTemplateTitle() : "";
    }

    @Override
    public NonNullOptionalFields getOptionalFields() {
        return this.joinedGridModelIsLoaded() ? this.joinedGridModel.optionalFields() : null;
    }

    @Override
    public void saveEntry(final String entryValue) {
        if (this.joinedGridModelIsLoaded()) {
            final EntryModel activeEntryModel =
                    this.joinedGridModel.getActiveEntryModel();
            if (activeEntryModel instanceof IncludedEntryModel) {
                final EntriesTable entriesTable =
                        this.entriesTable();
                if (null != entriesTable) {
                    final IncludedEntryModel
                            activeIncludedEntryModel =
                            (IncludedEntryModel)
                                    activeEntryModel;
                    if (this.getUnique()) {
                        final String oldEntryValue = activeIncludedEntryModel.getValue();
                        final CheckedIncludedEntryModel
                                checkedIncludedEntryModel =
                                (CheckedIncludedEntryModel)
                                        activeIncludedEntryModel;
                        try {
                            checkedIncludedEntryModel.checkThenSetValue(entryValue) /* throws */;
                        } catch (final
                        CheckedIncludedEntryModel.CheckException
                                e) {
                            if (e instanceof UniqueEntryModels.DuplicateCheckException) {
                                {
                                    final String message = e.getMessage();
                                    this.handleDuplicateCheckException(
                                            null == message ? e.toString() : message);
                                }
                                this.setEntry(oldEntryValue);
                            }
                            return;
                        }
                    } else activeIncludedEntryModel.setValue(entryValue);

                    entriesTable.insertOrUpdate(activeIncludedEntryModel);
                }
            }
            this.goToNext(activeEntryModel);
        }
    }

    @Override
    public void clearEntry() {
        this.saveEntry(null);
    }
    // endregion
    // endregion

    // region Public Methods
    public boolean joinedGridModelIsLoaded() {
        return null != this.joinedGridModel;
    }

    public void populateFragments() {
        if (null != this.gridDisplayFragment) this.gridDisplayFragment.populate();
        this.populateDataEntryFragment();
    }

    // region loadJoinedGridModel() Public Methods
    public void loadJoinedGridModel(@IntRange(from = 0) final long gridId) {
        if (Model.illegal(gridId))
            this.joinedGridModel = null;
        else {
            final GridsTable gridsTable = this.gridsTable();
            this.joinedGridModel = null == gridsTable ? null : gridsTable.get(gridId);
        }
    }

    public void loadJoinedGridModelThenPopulate(@IntRange(from = 0) final long gridId) {
        this.loadJoinedGridModel(gridId);
        this.populateFragments();
    }
    // endregion

    public void reloadIfNecessary() {
        if (Utils.gridsTableNeedsReloading(
                this.gridsTableInstance, this.activity))
            this.gridsTableInstance = null;

        if (Utils.entriesTableNeedsReloading(
                this.entriesTableInstance, this.activity))
            this.entriesTableInstance = null;

        if (Utils.joinedGridModelNeedsReloading(
                this.joinedGridModel, this.activity))
            this.loadJoinedGridModelThenPopulate(this.joinedGridModel.getId());
    }

    // region Barcode Public Methods
    @SuppressWarnings({"SameReturnValue"})
    public boolean scanBarcode() {
        this.barcodeScanner().scan();
        return true;
    }

    public boolean parseActivityResult(final int requestCode,
                                       final int resultCode, final Intent data) {
        final boolean handled;
        {
            final String barcodeScannerResult =
                    BarcodeScanner.parseActivityResult(
                            requestCode, resultCode, data);
            if (null == barcodeScannerResult)
                handled = false;
            else {
                this.setEntry(barcodeScannerResult);
                this.saveEntry(barcodeScannerResult);
                handled = true;
            }
        }
        return handled;
    }
    // endregion

    public void release() {
        if (null != this.disallowedDuplicateMediaPlayer)
            this.disallowedDuplicateMediaPlayer.release();
        if (null != this.rowOrColumnEndMediaPlayer) this.rowOrColumnEndMediaPlayer.release();
        if (null != this.gridEndMediaPlayer) this.gridEndMediaPlayer.release();
    }
    // endregion
}