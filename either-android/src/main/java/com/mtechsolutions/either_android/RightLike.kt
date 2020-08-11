package com.mtechsolutions.either_android

import com.mtechsolutions.either_android.EitherLike

interface RightLike : EitherLike {
    override fun isLeft(): Boolean = false
    override fun isRight(): Boolean = true
}