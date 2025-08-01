package com.wesleyaldrich.pancook.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wesleyaldrich.pancook.R
import com.wesleyaldrich.pancook.ui.theme.PancookTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.wesleyaldrich.pancook.ui.theme.nunito
import com.wesleyaldrich.pancook.ui.theme.poppins
import androidx.compose.ui.graphics.Color

/**
 * A reusable card component for displaying content with an image, title, and actions.
 *
 * @param modifier Modifier to be applied to the card.
 * @param imagePainter The painter for the image to display.
 * @param title The main title text for the card.
 * @param description A short description text for the card.
 * @param duration The duration text for the recipe (e.g., "30 min").
 * @param upvoteCount The number of upvotes for the item.
 * @param isBookmarked Boolean to indicate if the recipe is bookmarked.
 * @param onBookmarkClick Lambda to handle bookmark icon clicks.
 * @param onDeleteClick Lambda to handle delete icon clicks.
 * @param hideDeleteButton Boolean to control the visibility of the delete button.
 */
@Composable
fun ReusableCard(
    modifier: Modifier = Modifier,
    imagePainter: Painter,
    title: String,
    description: String,
    duration: String,
    upvoteCount: Int,
    isBookmarked: Boolean = false,
    onBookmarkClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    hideDeleteButton: Boolean = false // New parameter
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = "Card Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.TopCenter),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = duration,
                        fontFamily = nunito,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = colorResource(R.color.accent_yellow),
                        modifier = Modifier
                            .background(
                                colorResource(R.color.primary).copy(alpha = 0.75f),
                                RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                    Row {
                        // Bookmark button with layered icons for border effect
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(colorResource(R.color.primary).copy(alpha = 0.75f))
                                .clickable { onBookmarkClick() },
                            contentAlignment = Alignment.Center
                        ) {
                            // Border Icon (always white, slightly larger)
                            Icon(
                                imageVector = Icons.Filled.Bookmark,
                                contentDescription = "Bookmark Border",
                                tint = Color.White, // Fixed white for the border
                                modifier = Modifier.size(20.dp) // Slightly larger than the fill icon
                            )
                            // Fill Icon (dynamic color, slightly smaller)
                            Icon(
                                imageVector = Icons.Filled.Bookmark,
                                contentDescription = "Bookmark Fill",
                                tint = if (isBookmarked) colorResource(R.color.accent_yellow) else colorResource(R.color.primary).copy(alpha = 0.75f), // Red when bookmarked, transparent when not
                                modifier = Modifier.size(16.dp) // Smaller than the border icon
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        // Delete button (conditionally visible)
                        if (!hideDeleteButton) { // Apply the new parameter here
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(colorResource(R.color.primary).copy(alpha = 0.75f))
                                    .clickable { onDeleteClick() },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = colorResource(R.color.accent_yellow),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp, bottom = 8.dp)
                        .background(
                            colorResource(R.color.primary).copy(alpha = 0.75f),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = "Upvotes",
                        tint = colorResource(R.color.accent_yellow),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = if (upvoteCount >= 1000) {
                            "${upvoteCount / 1000}k"
                        } else {
                            "$upvoteCount"
                        },
                        fontFamily = nunito,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(R.color.accent_yellow),
                        fontSize = 12.sp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = title,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                )
                Text(
                    text = description,
                    fontFamily = nunito,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReusableCardPreview() {
    PancookTheme {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            ReusableCard(
                imagePainter = painterResource(id = R.drawable.salad),
                title = "Delicious Salad",
                description = "by Nunuk",
                duration = "15 min",
                upvoteCount = 1234,
                isBookmarked = true,
                onBookmarkClick = {},
                onDeleteClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            ReusableCard(
                imagePainter = painterResource(id = R.drawable.salad),
                title = "Another Delicious Dish",
                description = "by Chef John",
                duration = "45 min",
                upvoteCount = 987,
                isBookmarked = false,
                onBookmarkClick = {},
                onDeleteClick = {},
                hideDeleteButton = true // Example of hidden delete button
            )
            Spacer(modifier = Modifier.height(16.dp))
            ReusableCard(
                imagePainter = painterResource(id = R.drawable.salad),
                title = "Quick Snack",
                description = "by Mary",
                duration = "5 min",
                upvoteCount = 10000,
                isBookmarked = true,
                onBookmarkClick = {},
                onDeleteClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            ReusableCard(
                imagePainter = painterResource(id = R.drawable.salad),
                title = "Long Cook Meal",
                description = "by Chef Gordon",
                duration = "2 hours",
                upvoteCount = 5000,
                isBookmarked = true,
                onBookmarkClick = {},
                onDeleteClick = {}
            )
        }
    }
}
