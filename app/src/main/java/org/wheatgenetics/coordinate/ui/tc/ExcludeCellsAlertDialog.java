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
 * org.wheatgenetics.coordinate.utils.Utils
 *
 * org.wheatgenetics.coordinate.model.TemplateModel
 */
class ExcludeCellsAlertDialog extends org.wheatgenetics.androidlibrary.AlertDialog
{
    // region Fields
    private android.widget.EditText                          editText     ;
    private org.wheatgenetics.coordinate.model.TemplateModel templateModel;
    // endregion

    private void excludeCells()
    {
        final int amount = org.wheatgenetics.coordinate.utils.Utils.convert(
            org.wheatgenetics.androidlibrary.Utils.getText(this.editText));
        if (amount > 0)
        {
            assert null != this.templateModel; this.templateModel.makeRandomCells(amount);
            this.cancelAlertDialog();
        }
    }

    ExcludeCellsAlertDialog(final android.app.Activity activity) { super(activity); }

    @java.lang.Override
    public void configure()
    {
        this.setTitle(org.wheatgenetics.coordinate.R.string.random).setCancelableToFalse();

        {
            final android.view.View view =
                this.layoutInflater().inflate(org.wheatgenetics.coordinate.R.layout.random, null);

            if (null == this.editText)
            {
                assert null != view; this.editText = (android.widget.EditText)
                    view.findViewById(org.wheatgenetics.coordinate.R.id.cellsEdit);
                assert null != this.editText;
            }
            this.editText.setText("1");

            this.setView(view);
        }

        this.setOKPositiveButton(null).setCancelNegativeButton();
    }

    void show(final org.wheatgenetics.coordinate.model.TemplateModel templateModel)
    {
        if (null != templateModel)
        {
            this.templateModel = templateModel; this.show();

            if (!this.positiveOnClickListenerHasBeenReplaced()) this.replacePositiveOnClickListener(
                new android.view.View.OnClickListener()
                {
                    @java.lang.Override
                    public void onClick(final android.view.View view)
                    {
                        org.wheatgenetics.coordinate.ui.tc.
                            ExcludeCellsAlertDialog.this.excludeCells();
                    }
                });
        }
    }
}