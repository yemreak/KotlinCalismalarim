 /**
     * İki parmak dokunma olayı için gereken en fazla süre
     * @see fastClickEvent
     */
    private val delayFastClickMillis: Long = 300

    /**
     * Daha önceden tıklanmış mı kontrolü
     *
     * @see fastClickEvent İki kez hızlıca tıklanma kontrolü için kullanılır
     */
    private var justClicked = false

    private var doubleTouchConfirmed = false
    private var singleTouchConfirmed = false

/**
     * Ard arda hızlı tıklama olayında istenen işlemleri yapma
     *
     * @param onOnceClick Bir kez tıklandığında yapılacak işlemler
     * @param onTwiceClicks İki kez *hızlıca* tıklandığında yapılacak işlemler
     */
    protected fun fastClickEvent(e: MotionEvent, onOnceClick: () -> Unit, onTwiceClicks: () -> Unit): Boolean {
        if (e.action == MotionEvent.ACTION_DOWN) // Dokunma eylemi ise bu işlemler yapılacak, dokunduktan sonrası bizi ilgilendirmiyor.
            when (justClicked) {
                false -> {
                    justClicked = true

                    Handler().postDelayed({
                        if (justClicked) {
                            onOnceClick()
                        }
                        justClicked = false
                    }, delayFastClickMillis)
                }
                true -> {
                    onTwiceClicks()
                    justClicked = false
                }
            }

        return true
    }

    /**
     * 2 parmak ile dokunma olayında istenen işlemleri yapma
     *
     * @param onSingleTouch Bir kez dokunulduğunda yapılacak işlemler
     * @param onDoubleTouch 2 kez dokunulduğunda  yapılacak işlemler
     *
     * Not: İlk dokunma işlemi hep çalışıyor :/ [MotionEvent.ACTION_DOWN]
     */
    protected fun multiTouchEvent(e: MotionEvent, onSingleTouch: () -> Unit, onDoubleTouch: () -> Unit): Boolean {
        if (e.action == MotionEvent.ACTION_DOWN) // İlk aksiyonu belirlemek oldukça karmaşık, tek parmak diye ele alıyoruz.
            onSingleTouch()
        else {
            when (singleTouchConfirmed) {
                true -> {
                    singleTouchConfirmed = e.action != MotionEvent.ACTION_UP // Son aksiyonda değeri sıfırlıyoruz.
                    onSingleTouch()
                }
                false -> {
                    when (doubleTouchConfirmed) {
                        false -> {
                            singleTouchConfirmed = e.pointerCount == 1
                            doubleTouchConfirmed = e.pointerCount == 2

                            if (singleTouchConfirmed)
                                onSingleTouch()
                            else
                                onDoubleTouch()
                        }
                        true -> {
                            doubleTouchConfirmed = e.action != MotionEvent.ACTION_UP // Son aksiyonda değeri sıfırlıyoruz.

                            if (e.pointerCount == 2) // Sadece iki parmak işlemleri için metot çalışmalı, diğer durumlarda çalışmamalı. (diğer durumlar da var)
                                onDoubleTouch()
                        }
                    }
                }
            }
        }

        return true
    }