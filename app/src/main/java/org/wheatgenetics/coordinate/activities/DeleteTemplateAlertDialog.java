package org.wheatgenetics.coordinate.activities;

/**
 * Uses:
 * android.app.AlertDialog.Builder
 * android.content.Context
 * android.content.DialogInterface
 * android.content.DialogInterface.OnClickListener
 *
 * org.wheatgenetics.coordinate.R
 */
class DeleteTemplateAlertDialog extends java.lang.Object
{
    interface Handler { public abstract void deleteTemplate(int i); }

    // region Fields
    private final android.content.Context                                                   context;
    private final org.wheatgenetics.coordinate.activities.DeleteTemplateAlertDialog.Handler handler;

    private android.app.AlertDialog.Builder                 builder                 = null;
    private android.content.DialogInterface.OnClickListener onClickListenerInstance = null;
    // endregion

    // region Private Methods
    private void deleteTemplate(final int i)
    { assert null != this.handler; this.handler.deleteTemplate(i); }

    private android.content.DialogInterface.OnClickListener onClickListener()
    {
        if (null == this.onClickListenerInstance) this.onClickListenerInstance =
            new android.content.DialogInterface.OnClickListener()
            {
                @java.lang.Override
                public void onClick(final android.content.DialogInterface dialogInterface,
                final int i)
                {
                    org.wheatgenetics.coordinate.activities.
                        DeleteTemplateAlertDialog.this.deleteTemplate(i);
                }
            };
        return this.onClickListenerInstance;
    }
    // endregion

    DeleteTemplateAlertDialog(final android.content.Context context,
    final org.wheatgenetics.coordinate.activities.DeleteTemplateAlertDialog.Handler handler)
    { super(); this.context = context; this.handler = handler; }

    void show(final java.lang.CharSequence items[])
    {
        if (null == this.builder)
        {
            this.builder = new android.app.AlertDialog.Builder(this.context);
            this.builder.setTitle(org.wheatgenetics.coordinate.R.string.delete_template);
        }
        this.builder.setItems(items, this.onClickListener());
        this.builder.create().show();
    }
}