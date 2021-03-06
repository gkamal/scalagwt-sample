/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.sample.showcase.client.content.panels

import com.google.gwt.core.client.GWT
import com.google.gwt.core.client.RunAsyncCallback
import com.google.gwt.i18n.client.Constants
import com.google.gwt.sample.showcase.client.ContentWidget
import com.google.gwt.sample.showcase.client.ShowcaseAnnotations.ShowcaseData
import com.google.gwt.sample.showcase.client.ShowcaseAnnotations.ShowcaseSource
import com.google.gwt.user.client.rpc.AsyncCallback
import com.google.gwt.user.client.ui.Button
import com.google.gwt.user.client.ui.HorizontalPanel
import com.google.gwt.user.client.ui.Widget

object CwHorizontalPanel {
  /**
   * The constants used in this Content Widget.
   */
  @ShowcaseSource
  trait CwConstants extends Constants with ContentWidget.CwConstants {
    def cwHorizontalPanelButton(): String

    def cwHorizontalPanelDescription(): String

    def cwHorizontalPanelName(): String
  }
}

/**
 * Example file.
 */
class CwHorizontalPanel(constants: CwHorizontalPanel.CwConstants) extends ContentWidget(constants) {

  override def getDescription() = constants.cwHorizontalPanelDescription

  override def getName() = constants.cwHorizontalPanelName

  override def hasStyle() = false

  /**
   * Initialize this example.
   */
  @ShowcaseSource
  override def onInitialize(): Widget = {
    // Create a Horizontal Panel
    val hPanel = new HorizontalPanel()
    hPanel.setSpacing(5)

    // Add some content to the panel
    for (i <- 1 until 5) {
      hPanel.add(new Button(constants.cwHorizontalPanelButton + " " + i))
    }

    // Return the content
    hPanel.ensureDebugId("cwHorizontalPanel")
    hPanel
  }

  override def asyncOnInitialize(callback: AsyncCallback[Widget]) = {
    GWT.runAsync(new RunAsyncCallback() {
      def onFailure(caught: Throwable) = callback.onFailure(caught)

      def onSuccess() = callback.onSuccess(onInitialize())
    })
  }
}
