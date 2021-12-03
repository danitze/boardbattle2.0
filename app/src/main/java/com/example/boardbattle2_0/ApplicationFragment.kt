package com.example.boardbattle2_0

import androidx.fragment.app.Fragment

abstract class ApplicationFragment: Fragment() {
    abstract fun navigateUp(): Boolean
}