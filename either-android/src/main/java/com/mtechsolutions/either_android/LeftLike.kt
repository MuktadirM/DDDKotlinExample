package com.mtechsolutions.either_android

import com.mtechsolutions.either_android.EitherLike

interface LeftLike : EitherLike {
    override fun isLeft(): Boolean = true
    override fun isRight(): Boolean = false
}