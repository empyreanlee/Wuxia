package com.example.wuxia

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wuxia.model.Sage
import com.example.wuxia.model.SageSource
import com.example.wuxia.ui.theme.WuxiaTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SageList(sage: List<Sage>, modifier: Modifier=Modifier,contentPadding: PaddingValues =PaddingValues(0.dp)
){
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn(contentPadding = contentPadding) {
            itemsIndexed(sage) { idx, sage ->
                SageItemCard(
                    sage = sage,
                    modifier = modifier.padding(6.dp)
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = {it * (idx +1)}
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun SageItemCard(sage: Sage,modifier: Modifier = Modifier){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier){
        Row(modifier = modifier.padding(12.dp).sizeIn(minHeight = 72.dp)) {
            Column(modifier = modifier.weight(1f)) {
                Text(
                    text = stringResource(sage.name),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(sage.desc),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier=modifier.padding(16.dp))
            Box(
                modifier = modifier.height(72.dp).clip(
                    RoundedCornerShape(8.dp)
                )
                ) {
                Image(
                    painter = painterResource(sage.image),
                    contentDescription = null,

                )
            }
        }
    }
}

@Preview("Sages List")
@Composable
fun WuxiaThemePreview() {
    WuxiaTheme(darkTheme = false){
        Surface(
            color = MaterialTheme.colorScheme.background
        ){
            SageList(sage = SageSource.sages)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ScoobyDarkThemePreview() {
    WuxiaTheme(darkTheme = true) {
    }
}