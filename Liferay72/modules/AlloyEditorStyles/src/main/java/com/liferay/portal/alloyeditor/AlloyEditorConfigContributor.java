/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.portal.alloyeditor;

import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    property = {
        "editor.name=alloyeditor",
        "service.ranking:Integer=100"
    },

    service = EditorConfigContributor.class
)
public class AlloyEditorConfigContributor extends BaseEditorConfigContributor {

	@Override
	public void populateConfigJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

		JSONObject toolbarsJSONObject = jsonObject.getJSONObject("toolbars");

		if (toolbarsJSONObject == null) {
			toolbarsJSONObject = JSONFactoryUtil.createJSONObject();
		}

		JSONObject stylesToolbar = toolbarsJSONObject.getJSONObject("styles");

		if (stylesToolbar == null) {
			stylesToolbar = JSONFactoryUtil.createJSONObject();
		}

		JSONArray selectionsJSONArray = stylesToolbar.getJSONArray("selections");

		for (int i = 0; i < selectionsJSONArray.length(); i++) {

			System.out.println("iter:" + i);

			JSONObject selection = selectionsJSONArray.getJSONObject(i);

			if (Objects.equals(selection.get("name"), "text")) {
				JSONArray buttons = selection.getJSONArray("buttons");

				buttons.put("imageLeft");
				buttons.put("imageCenter");
				buttons.put("imageRight");
			}

		}

		stylesToolbar.put("selections", selectionsJSONArray);

		toolbarsJSONObject.put("styles", stylesToolbar);

		jsonObject.put("toolbars", toolbarsJSONObject);
	}

}