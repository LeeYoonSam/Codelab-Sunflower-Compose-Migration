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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModel

/**
 * 컴포저블에는 자체 ViewModel 인스턴스가 없으며
 * 컴포저블과 해당 컴포저 코드(활동 또는 조각)를 호스팅하는 수명 주기 소유자 간에 동일한 인스턴스가 공유됩니다.
 *
 * **ViewModel을 사용할 수 없거나 해당 종속성을 컴포저블에 전달하지 않으려는 경우 컴포저블 내에서 viewModel** 함수를 사용하여
 * ViewModel의 인스턴스를 가져올 수 있습니다.
 */
@Composable
fun PlantDetailDescription(plantDetailViewModel: PlantDetailViewModel) {
    /**
     * 이를 통해 이미 PlantDetailViewModel의 LiveData<Plant> 필드에 액세스하여 식물 이름을 얻을 수 있습니다.
     * 컴포저블에서 LiveData를 관찰하려면 LiveData.observeAsState() 함수를 사용하세요.
     *
     * 참고: LiveData.observeAsState()는 LiveData 관찰을 시작하고 State 객체를 통해 값을 나타냅니다.
     * LiveData에 새 값이 게시될 때마다 반환된 State가 업데이트되어 모든 State.value 사용이 재구성됩니다.
     *
     * LiveData에서 내보낸 값이 null일 수 있으므로 null 검사에서 사용을 래핑해야 합니다.
     * 그 때문에 그리고 재사용성을 위해 LiveData 소비를 분할하고 다른 컴포저블에서 수신하는 것이 좋은 패턴입니다.
     * 따라서 Plant 정보를 표시할 `PlantDetailContent`라는 새 구성 요소를 만듭니다.
     */

    // VM의 LiveData<Plant> 필드에서 오는 값을 관찰합니다.
    // import androidx.compose.runtime.getValue, by for State를 사용하여 val의 속성 위임을 허용합니다.
    val plant by plantDetailViewModel.plant.observeAsState()

    // 식물이 null이 아닌 경우 내용을 표시합니다.
    plant?.let {
        PlantDetailContent(it)
    }
}

@Composable
fun PlantDetailContent(plant: Plant) {
    PlantName(plantName = plant.name)
}

@Preview
@Composable
private fun PlantDetailContentPreview() {
    val plant = Plant("id", "Apple", "description", 3, 30, "")
    MaterialTheme {
        PlantDetailContent(plant)
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