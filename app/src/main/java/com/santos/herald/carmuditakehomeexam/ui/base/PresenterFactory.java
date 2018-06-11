package com.santos.herald.carmuditakehomeexam.ui.base;

public interface PresenterFactory<P extends BasePresenter> {

    P create();
}