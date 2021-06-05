import React from 'react';
import httpService from '../services/httpService';
import { Row, Col, Container, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class ProductList extends React.Component {
  constructor(props) {
    super(props);

    this.getProductList = this.getProductList.bind(this);
    this.deleteProduct = this.deleteProduct.bind(this);

    this.state = {
      products: [],
    };
  }

  getProductList() {
    httpService
      .get('/products')
      .then((response) => {
        console.log('getProductList Response :');
        console.log(response.data);

        this.setState({
          products: response.data,
        });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  deleteProduct(id, e) {
    httpService
      .delete('/products/' + id)
      .then((response) => {
        console.log('deleteProduct Response :');
        console.log(response);
        this.getProductList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  componentDidMount() {
    this.getProductList();
  }

  render() {
    console.log(this.state);
    const productList = this.state.products.map((product, index) => {
      let store = product.store;
      if (!store) {
        store = {
          storeID: 0,
          name: '',
          address: '',
        };
      }
      return (
        <Container>
          <br />
          <Row key={index}>
            <Col>
              {product.productID}
            </Col>
            <Col>
              {product.brand}
            </Col>
            <Col>
              {product.productName}
            </Col>
            <Col>
              {product.quantity}
            </Col>
            <Col>
              {store.storeID} {store.name}
            </Col>
            <Col>
              {product.customers.length}
            </Col>
            <Col>
              <Link
                to={{
                  pathname: '/update',
                  state: {
                    id: product.productID,
                  },
                }}
              >
                <Button variant="dark">Update</Button>
              </Link>{' '}
              <Button
                variant="light"
                onClick={(e) => this.deleteProduct(product.productID, e)}
              >
                Delete
              </Button>
            </Col>
          </Row>
        </Container>
      );
    });

    return (
      <Container>
        <h3 style={{ textAlign: 'center' }}>Product List</h3>
        <Link to={'/create'}>
          <Button variant="outline-dark">Add new Product</Button>
        </Link>
        <Container>
          <Row>
            <Col>Product ID</Col>
            <Col>Brand</Col>
            <Col>Product Name</Col>
            <Col>Quantity</Col>
            <Col>Store</Col>
            <Col>Customers</Col>
            <Col>Actions</Col>
          </Row>
          {productList}
        </Container>
      </Container>
    );
  }
}
