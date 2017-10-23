package org.wheatgenetics.coordinate.ui.tc;

/**
 * Uses:
 * android.app.Activity
 * android.view.View
 * android.view.View.OnClickListener
 * android.widget.EditText
 *
 * org.wheatgenetics.androidlibrary.AlertDialog
 * org.wheatgenetics.androidlibrary.Utils
 *
 * org.wheatgenetics.coordinate.R
 *
 * org.wheatgenetics.coordinate.model.TemplateModel
 */
class AssignTitleRowsColsAlertDialog extends org.wheatgenetics.androidlibrary.AlertDialog
{
    // region Types
                   interface Handler     { public abstract void handleAssignDone(); }
    private static class     Unspecified extends java.lang.Exception {}
    // endregion

    // region Fields
    private final org.wheatgenetics.coordinate.ui.tc.AssignTitleRowsColsAlertDialog.Handler handler;

    private android.widget.EditText titleTextEdit, rowsTextEdit, colsTextEdit;
    private org.wheatgenetics.coordinate.model.TemplateModel    templateModel;
    // endregion

    // region Private Methods
    // region convert() Private Methods
    private static java.lang.String convert(final int integer)
    { return integer <= 0 ? "" : java.lang.String.valueOf(integer); }

    private static int convert(final java.lang.String string)
    throws org.wheatgenetics.coordinate.ui.tc.AssignTitleRowsColsAlertDialog.Unspecified
    {
        if (0 == string.length())
            throw
                new org.wheatgenetics.coordinate.ui.tc.AssignTitleRowsColsAlertDialog.Unspecified();
        else
        {
            final int i = java.lang.Integer.valueOf(string);
            if (i < 1)
                throw new
                    org.wheatgenetics.coordinate.ui.tc.AssignTitleRowsColsAlertDialog.Unspecified();
            else
                return i;
        }
    }
    // endregion

    private void assignTemplate()
    {
        final java.lang.String title =
            org.wheatgenetics.androidlibrary.Utils.getText(this.titleTextEdit);
        if (0 == title.length())
            this.showToast(org.wheatgenetics.coordinate.R.string.template_no_name);
        else
            try
            {
                final int rows =
                    org.wheatgenetics.coordinate.ui.tc.AssignTitleRowsColsAlertDialog.convert(
                        org.wheatgenetics.androidlibrary.Utils.getText(this.rowsTextEdit));
                try
                {
                    final int cols =
                        org.wheatgenetics.coordinate.ui.tc.AssignTitleRowsColsAlertDialog.convert(
                            org.wheatgenetics.androidlibrary.Utils.getText(this.colsTextEdit));

                    assert null != this.templateModel; this.templateModel.assign(
                        /* title => */ title, /* rows => */ rows, /* cols => */ cols);
                    this.cancelAlertDialog();
                    assert null != this.handler; this.handler.handleAssignDone();
                }
                catch (final
                org.wheatgenetics.coordinate.ui.tc.AssignTitleRowsColsAlertDialog.Unspecified e)
                { this.showToast(org.wheatgenetics.coordinate.R.string.no_cols); }
            }
            catch (
            final org.wheatgenetics.coordinate.ui.tc.AssignTitleRowsColsAlertDialog.Unspecified e)
            { this.showToast(org.wheatgenetics.coordinate.R.string.no_rows); }
    }
    // endregion

    AssignTitleRowsColsAlertDialog(final android.app.Activity activity,
    final org.wheatgenetics.coordinate.ui.tc.AssignTitleRowsColsAlertDialog.Handler handler)
    { super(activity); this.handler = handler; }

    @java.lang.Override
    public void configure()
    {
        this.setTitle(org.wheatgenetics.coordinate.R.string.template_new);

        {
            final android.view.View view =
                this.inflate(org.wheatgenetics.coordinate.R.layout.template_new);

            assert null != view;
            if (null == this.titleTextEdit) this.titleTextEdit = (android.widget.EditText)
                view.findViewById(org.wheatgenetics.coordinate.R.id.nameEdit);
            if (null == this.rowsTextEdit) this.rowsTextEdit = (android.widget.EditText)
                view.findViewById(org.wheatgenetics.coordinate.R.id.rowsEdit);
            if (null == this.colsTextEdit) this.colsTextEdit = (android.widget.EditText)
                view.findViewById(org.wheatgenetics.coordinate.R.id.colsEdit);

            this.setView(view);
        }

        this.setPositiveButton(org.wheatgenetics.coordinate.R.string.next)
            .setCancelNegativeButton();
    }

    void show(final org.wheatgenetics.coordinate.model.TemplateModel templateModel)
    {
        if (null != templateModel)
        {
            this.templateModel = templateModel;
            assert null != this.titleTextEdit; this.titleTextEdit.setText("");
            assert null != this.rowsTextEdit ; this.rowsTextEdit.setText(
                org.wheatgenetics.coordinate.ui.tc.AssignTitleRowsColsAlertDialog.convert(
                    this.templateModel.getRows()));
            assert null != this.colsTextEdit; this.colsTextEdit.setText(
                org.wheatgenetics.coordinate.ui.tc.AssignTitleRowsColsAlertDialog.convert(
                    this.templateModel.getCols()));
            this.show();

            if (!this.positiveOnClickListenerHasBeenReplaced()) this.replacePositiveOnClickListener(
                new android.view.View.OnClickListener()
                {
                    @java.lang.Override
                    public void onClick(final android.view.View view)
                    {
                        org.wheatgenetics.coordinate.ui.tc
                            .AssignTitleRowsColsAlertDialog.this.assignTemplate();
                    }
                });
        }
    }
}