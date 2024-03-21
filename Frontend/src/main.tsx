import { BrowserRouter } from 'react-router-dom';
import { App } from './app';
import { createRoot } from 'react-dom/client';
import { rootReducer } from './app/appProvider';
import React from 'react';
import { createStore } from 'redux';
import { Provider } from 'react-redux';

const container = document.getElementById('root');
const root = createRoot(container!);
const store = createStore(rootReducer);

root.render(
    <Provider store={store}>
        <BrowserRouter>
            <App />
        </BrowserRouter>
    </Provider>,
);
