package org.wheatgenetics.coordinate.model;

/**
 * Uses:
 * android.content.Context
 * android.content.DialogInterface
 * android.content.DialogInterface.OnCancelListener
 * android.content.Intent
 * android.net.Uri
 * android.os.AsyncTask
 * android.support.annotation.RestrictTo
 * android.support.annotation.RestrictTo.Scope
 *
 * org.wheatgenetics.androidlibrary.ProgressDialog
 * org.wheatgenetics.androidlibrary.Utils
 *
 * org.wheatgenetics.coordinate.R
 * org.wheatgenetics.coordinate.Utils
 */
@java.lang.SuppressWarnings("ClassExplicitlyExtendsObject")
abstract class Exporter extends java.lang.Object
{
    abstract static class AsyncTask
    extends android.os.AsyncTask<java.lang.Void, java.lang.String, java.lang.Boolean>
    implements android.content.DialogInterface.OnCancelListener
    {
        // region Fields
        private final android.content.Context                         context       ;
        private final org.wheatgenetics.androidlibrary.ProgressDialog progressDialog;
        private final java.io.File                                    exportFile    ;

        private java.lang.String message = null;
        // endregion

        private void confirm(final int message, final java.lang.Runnable yesRunnable)
        {
            @java.lang.SuppressWarnings("ClassExplicitlyExtendsObject")
            class NoRunnable extends java.lang.Object implements java.lang.Runnable
            {
                @java.lang.Override
                public void run()
                { org.wheatgenetics.coordinate.model.Exporter.AsyncTask.this.share(); }
            }

            org.wheatgenetics.coordinate.Utils.confirm(
                this.context, message, yesRunnable, new NoRunnable());
        }

        @android.support.annotation.RestrictTo(
            android.support.annotation.RestrictTo.Scope.SUBCLASSES)
        AsyncTask(final android.content.Context context, final java.io.File exportFile)
        {
            super();

            this.context        = context;
            this.progressDialog = new org.wheatgenetics.androidlibrary.ProgressDialog(this.context,
                /* title => */ org.wheatgenetics.coordinate.R.string.ExporterProgressDialogTitle,
                /* initialMessage => */
                    org.wheatgenetics.coordinate.R.string.ExporterProgressDialogInitialMessage,
                /* onCancelListener => */ this);
            this.exportFile = exportFile;
        }

        // region Overridden Methods
        @java.lang.Override
        protected void onPreExecute() { super.onPreExecute(); this.progressDialog.show(); }

        @java.lang.Override @java.lang.SuppressWarnings("ResultOfMethodCallIgnored")
        protected java.lang.Boolean doInBackground(final java.lang.Void... params)
        {
            if (null == this.exportFile)
                return false;
            else
            {
                if (this.exportFile.exists()) this.exportFile.delete();
                return this.export();
            }
        }

        @java.lang.Override
        protected void onProgressUpdate(final java.lang.String... messages)
        {
            if (null != messages)
            {
                final java.lang.String message = messages[0];
                if (null != message) this.progressDialog.setMessage(message);
            }
        }

        @java.lang.Override
        protected void onPostExecute(final java.lang.Boolean result)
        {
            this.progressDialog.dismiss();
            if (null != result && result)
                this.handleExportSuccess(this.exportFile);
            else
                org.wheatgenetics.coordinate.Utils.alert(
                    /* context => */ this.context                                           ,
                    /* title   => */ org.wheatgenetics.coordinate.R.string.ExporterFailTitle,
                    /* message => */ org.wheatgenetics.javalib.Utils.replaceIfNull(this.message,
                        this.getString(org.wheatgenetics.coordinate.R.string.ExporterFailMessage)));
        }

        // region android.content.DialogInterface.OnCancelListener Overridden Method
        @java.lang.Override
        public void onCancel(final android.content.DialogInterface dialog) { this.cancel(); }
        // endregion
        // endregion

        // region Package Methods
        // region Exporter.AsyncTask Package Methods
        @android.support.annotation.RestrictTo(
            android.support.annotation.RestrictTo.Scope.SUBCLASSES)
        abstract boolean export();

        void cancel() { this.cancel(/* mayInterruptIfRunning => */ true); }

        @android.support.annotation.RestrictTo(
            android.support.annotation.RestrictTo.Scope.SUBCLASSES)
        abstract void handleExportSuccess(final java.io.File exportFile);

        @android.support.annotation.RestrictTo(
            android.support.annotation.RestrictTo.Scope.SUBCLASSES)
        java.lang.String getString(final int resId)
        { assert null != this.context; return this.context.getString(resId); }
        // endregion

        // region Subclass Package Methods
        // region export() Subclass Package Methods
        @android.support.annotation.RestrictTo(
            android.support.annotation.RestrictTo.Scope.SUBCLASSES)
        java.io.File getExportFile() { return this.exportFile; }

        @android.support.annotation.RestrictTo(
            android.support.annotation.RestrictTo.Scope.SUBCLASSES)
        void makeExportFileDiscoverable()
        {
            org.wheatgenetics.androidlibrary.Utils.makeFileDiscoverable(
                this.context, this.exportFile);
        }
        // endregion

        // region handleExportSuccess() Subclass Package Method
        @android.support.annotation.RestrictTo(
            android.support.annotation.RestrictTo.Scope.SUBCLASSES)
        void share()
        {
            if (null != this.exportFile)
            {
                final android.content.Intent intent =
                    new android.content.Intent(android.content.Intent.ACTION_SEND);

                intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                intent.putExtra(android.content.Intent.EXTRA_STREAM,
                    android.net.Uri.parse(this.exportFile.getAbsolutePath()));
                intent.setType("text/plain");

                assert null != this.context;
                this.context.startActivity(android.content.Intent.createChooser(intent,
                    this.getString(org.wheatgenetics.coordinate.R.string.ExporterShareTitle)));
            }
        }
        // endregion
        // endregion

        // region GridExporter.AsyncTask Package Methods
        @android.support.annotation.RestrictTo(
            android.support.annotation.RestrictTo.Scope.SUBCLASSES)
        void setMessage(final int resId) { this.message = this.getString(resId); }

        @android.support.annotation.RestrictTo(
            android.support.annotation.RestrictTo.Scope.SUBCLASSES)
        void alert(final int message, final java.lang.Runnable yesRunnable)
        {
            org.wheatgenetics.coordinate.Utils.alert(this.context,
                org.wheatgenetics.coordinate.R.string.ExporterSuccessTitle, new java.lang.Runnable()
                    {
                        @java.lang.Override
                        public void run()
                        {
                            org.wheatgenetics.coordinate.model.Exporter.AsyncTask.this.confirm(
                                message, yesRunnable);
                        }
                    });
        }
        // endregion

        // region TemplateExporter.AsyncTask Package Method
        @android.support.annotation.RestrictTo(
            android.support.annotation.RestrictTo.Scope.SUBCLASSES)
        void alert()
        {
            org.wheatgenetics.coordinate.Utils.alert(
                /* context => */ this.context                                              ,
                /* message => */ org.wheatgenetics.coordinate.R.string.ExporterSuccessTitle);
        }
        // endregion
        // endregion
    }

    // region Public Methods
    public abstract void execute();
    public abstract void cancel ();
    // endregion
}