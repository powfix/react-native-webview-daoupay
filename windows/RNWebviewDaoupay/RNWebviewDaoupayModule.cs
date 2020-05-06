using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Webview.Daoupay.RNWebviewDaoupay
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNWebviewDaoupayModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNWebviewDaoupayModule"/>.
        /// </summary>
        internal RNWebviewDaoupayModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNWebviewDaoupay";
            }
        }
    }
}
