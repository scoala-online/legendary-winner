import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import './app.css';

import CustomerList from './components/customerList.component';
// import CustomerUpdate from './components/customerUpdate.component';
// import CustomerCreate from './components/customerCreate.component';

import ProductList from './components/productList.component';

import StoreList from './components/storeList.component';

import ProductCartList from './components/productCartList.component';

import ProductCartKeyList from './components/ProductCartKeyList.component';

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route exact path={['/customers', '']} component={CustomerList} />
          {/* <Route exact path={'/update'} component={CustomerUpdate} />
          <Route exact path={'/create'} component={CustomerCreate} /> */}

          <Route exact path={['/products', '']} component={ProductList} />

          <Route exact path={['/stores', '']} component={StoreList} />

          <Route exact path={['/productCarts', '']} component={ProductCartList} />

          <Route exact path={['/productCartKeys', '']} component={ProductCartKeyList} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
