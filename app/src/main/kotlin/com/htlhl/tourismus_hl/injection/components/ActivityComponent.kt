package com.htlhl.tourismus_hl.injection.components

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.htlhl.tourismus_hl.injection.modules.ActivityModule
import com.htlhl.tourismus_hl.injection.modules.ViewModelModule
import com.htlhl.tourismus_hl.injection.qualifier.ActivityContext
import com.htlhl.tourismus_hl.injection.qualifier.ActivityDisposable
import com.htlhl.tourismus_hl.injection.qualifier.ActivityFragmentManager
import com.htlhl.tourismus_hl.injection.scopes.PerActivity
import com.htlhl.tourismus_hl.ui.base.feedback.Snacker
import com.htlhl.tourismus_hl.ui.base.navigator.Navigator
import com.htlhl.tourismus_hl.ui.main.MainActivity
import dagger.Component
import io.reactivex.disposables.CompositeDisposable

/* Copyright 2016 Patrick Löwenstein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ------
 *
 * FILE MODIFIED 2017 Tailored Media GmbH */
@PerActivity
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class), (ViewModelModule::class)])
interface ActivityComponent : ActivityComponentProvides {
    // create inject methods for your Activities here

    fun inject(activity: MainActivity)

}

interface ActivityComponentProvides : AppComponentProvides {
    @ActivityContext fun activityContext(): Context
    @ActivityFragmentManager fun defaultFragmentManager(): FragmentManager
    @ActivityDisposable fun activityDisposable(): CompositeDisposable

    fun navigator(): Navigator
    fun snacker(): Snacker
}
