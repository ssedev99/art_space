package com.example.artspace

data class ArtSpace(val title: String, val name: String, val year: String, val image: Int)

object ArtSpaceData {
    val items = listOf(
        ArtSpace(
            "Whispers of the Endless Fields",
            "First Name",
            "2021",
            R.drawable.image1
        ),
        ArtSpace(
            "Golden Horizon at the Tides",
            "Second Name",
            "2025",
            R.drawable.image2
        ),
        ArtSpace(
            "Embrace of the Rustic Haven",
            "Hello Good",
            "1999",
            R.drawable.image3
        )
    )
}