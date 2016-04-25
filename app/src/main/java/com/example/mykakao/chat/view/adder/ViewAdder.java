package com.example.mykakao.chat.view.adder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Stream;
import com.example.mykakao.chat.pojo.ChatData;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 재화 on 2016-04-24.
 */
public class ViewAdder {

    List<ViewElement> viewElements;

    public ViewAdder(List<ViewElement> viewElements){
        this.viewElements = viewElements;
    }

    public ViewAdder(){viewElements = new ArrayList<>();}

    public void addViewElement(ViewElement viewElement){
        viewElements.add(viewElement);
    }

    public void addAllViewElements(List<ViewElement> viewElements){
        this.viewElements = viewElements;
    }


    public int getViewId(ChatData data){
        return Stream.of(viewElements).filter(view-> {return data.getClass().getName() == view.getChatDataClass().getName();})
                .map(ViewElement::getViewId)
                .findFirst()
                .get();
    }

    public RecyclerView.ViewHolder getViewHolder(int viewId, ViewGroup parent) {
        ViewElement viewElement = Stream.of(viewElements)
                .filter(view -> viewId == view.getViewId())
                .findFirst()
                .get();
        return makeViewHolderInstance(viewElement, parent);
    }

    private RecyclerView.ViewHolder makeViewHolderInstance(ViewElement viewElement, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(viewElement.getResourceId(), parent, false);
        Constructor<? extends RecyclerView.ViewHolder> c;
        try {
            c = viewElement.getViewHolderClass().getConstructor(View.class);
            return c.newInstance(view);
        } catch (Exception exception) {
            Log.e("exception" , exception.toString());
            throw new RuntimeException(exception);
        }
    }

    public Class<?> getViewHolderClass(int viewId) {
        return Stream.of(viewElements)
                .filter(view -> view.getViewId() == viewId)
                .map(ViewElement::getViewHolderClass)
                .findFirst()
                .get();
    }

}
