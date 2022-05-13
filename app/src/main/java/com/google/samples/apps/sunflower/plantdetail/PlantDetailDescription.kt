/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.plantdetail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.samples.apps.sunflower.R

@Composable
fun PlantDetailDescription() {
    Surface {
        Text("Hello Compose")
    }
}

@Composable
fun PlantName(plantName: String) {
    Text(
        text = plantName,
        // 텍스트 스타일은 XML 코드에서 textAppearanceHeadline5에 매핑되는 MaterialTheme.typography.h5입니다.
        style = MaterialTheme.typography.h5,
        // modifier는 텍스트를 장식하여 XML 버전처럼 보이도록 조정합니다.
        modifier = Modifier
            // fillMaxWidth 수정자는 XML 코드의 android:layout_width="match_parent"에 해당합니다
            .fillMaxWidth()
            // dimensionResource 도우미 함수를 사용하는 View 시스템의 값인 margin_small의 수평 패딩
            .padding(horizontal = dimensionResource(R.dimen.margin_small))
            // wrapContentWidth를 사용하여 텍스트를 수평으로 정렬합니다
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Preview
@Composable
private fun PlantNamePreview() {
    MaterialTheme {
        PlantName("Apple")
    }
}