// IShizukuService.aidl
package com.strangerweather.rootlessuimods;

import android.os.IBinder;

interface IShizukuService {
    void destroy() = 16777114;

    IBinder getIOM() = 1;
}