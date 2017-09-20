package org.wheatgenetics.coordinate.ui;

/**
 * Uses:
 * android.app.Activity
 *
 * org.wheatgenetics.coordinate.model.TemplateModel
 *
 * org.wheatgenetics.coordinate.ui.ExtraNewTemplateAlertDialog
 * org.wheatgenetics.coordinate.ui.ExtraNewTemplateAlertDialog.Handler
 * org.wheatgenetics.coordinate.ui.NewTemplateAlertDialog
 * org.wheatgenetics.coordinate.ui.NewTemplateAlertDialog.Handler
 */
class TemplateCreator extends java.lang.Object implements
org.wheatgenetics.coordinate.ui.NewTemplateAlertDialog.Handler,
org.wheatgenetics.coordinate.ui.ExtraNewTemplateAlertDialog.Handler
{
    interface Handler { public abstract void handleTemplateCreated(); }

    // region Fields
    private final android.app.Activity                                    activity;
    private final org.wheatgenetics.coordinate.ui.TemplateCreator.Handler handler ;

    private org.wheatgenetics.coordinate.model.TemplateModel templateModel;

    private org.wheatgenetics.coordinate.ui.NewTemplateAlertDialog newTemplateAlertDialog = null;
    private org.wheatgenetics.coordinate.ui.ExtraNewTemplateAlertDialog
        extraNewTemplateAlertDialog = null;
    // endregion

    TemplateCreator(final android.app.Activity activity,
    final org.wheatgenetics.coordinate.ui.TemplateCreator.Handler handler)
    { super(); this.activity = activity; this.handler = handler; }

    // region Overridden Methods
    // region org.wheatgenetics.coordinate.ui.NewTemplateAlertDialog.Handler Overridden Method
    @java.lang.Override
    public void handleNewTemplateNext()
    {
        if (null == this.extraNewTemplateAlertDialog) this.extraNewTemplateAlertDialog =
            new org.wheatgenetics.coordinate.ui.ExtraNewTemplateAlertDialog(this.activity, this);
        // this.extraNewTemplateAlertDialog.show();                                // TODO: Prevent!
        this.extraNewTemplateAlertDialog.show(this.templateModel);
    }
    // endregion

    // region org.wheatgenetics.coordinate.ui.ExtraNewTemplateAlertDialog Overridden Method
    @java.lang.Override
    public void handleExtraNewTemplateNext()
    { assert null != this.handler; this.handler.handleTemplateCreated(); }
    // endregion
    // endregion

    void create(final org.wheatgenetics.coordinate.model.TemplateModel templateModel)
    {
        if (null != templateModel)
        {
            if (null == this.newTemplateAlertDialog) this.newTemplateAlertDialog =
                new org.wheatgenetics.coordinate.ui.NewTemplateAlertDialog(this.activity, this);
            this.templateModel = templateModel; this.templateModel.clearExcludesAndOptionalFields();
            this.newTemplateAlertDialog.show(this.templateModel);
        }
    }
}