import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import './app.css';

import CustomerCreate from './components/customerCreate.component';
import ProductCreate from './components/productCreate.component';
import StoreCreate from './components/storeCreate.component';

function App() {
  return (
    <div className="App">
      <h1 style={{ padding: 16 }}>
        <b>My Online Store</b>
      </h1>
      <Router>
        <Switch>
          <Route exact path={'/customers/create'} component={CustomerCreate} />
          <Route exact path={'/products/create'} component={ProductCreate} />
          <Route exact path={'/stores/create'} component={StoreCreate} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
