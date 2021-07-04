import React from 'react';
import httpService from '../services/httpService';
import { Row, Col, Container, Button } from 'react-bootstrap';
import { Link, withRouter } from 'react-router-dom';

class ProductCreate extends React.Component {
  constructor(props) {
    super(props);

    this.createProduct = this.createProduct.bind(this);

    this.state = {
      brand: '',
      productName: '',
      quantity: 0,
      customers: [],
      store: null,
    };
  }

  createProduct(product) {
    let data = {
      brand: product.brand,
      productName: product.productName,
      quantity: product.quantity,
      customers: product.customers,
      store: product.store,
    };

    if (product.store !== null) {
      console.log(product.store);
      data = {
        ...data,
        store: {
          id: product.store,
        },
      };
    } else {
      data = {
        ...data,
        store: null,
      };
    }

    console.log(data);

    httpService
      .post('/products', data)
      .then((response) => {
        console.log('createProduct Response :');
        console.log(response.data);
        this.props.history.push('/products');
      })
      .catch((e) => {
        console.log(e);
      });
  }

  render() {
    const product = this.state;

    return (
      <Container>
        <h2>Create Product</h2>
        <br />
        <Container>
          <Row>
            <Col>Brand:</Col>
            <Col>
              <input
                type="text"
                placeholder={product.brand}
                required
                onChange={(e) => this.setState({ brand: e.target.value })}
              />
            </Col>
          </Row>
          <Row>
            <Col>Product Name:</Col>
            <Col>
              <input
                type="text"
                placeholder={product.productName}
                required
                onChange={(e) => this.setState({ productName: e.target.value })}
              />
            </Col>
          </Row>
          <Row>
            <Col>Quantity:</Col>
            <Col>
              <input
                type="text"
                placeholder={product.quantity}
                required
                onChange={(e) => this.setState({ quantity: e.target.value })}
              />
            </Col>
          </Row>
          <br />
          <Link to={'/products'}>
            <Button variant="outline-primary">Back to Products</Button>
          </Link>
          <Button
            variant={'success'}
            style={{ marginLeft: '5vw' }}
            onClick={() => this.createProduct(product)}
          >
            Create
          </Button>
        </Container>
      </Container>
    );
  }
}

export default withRouter(ProductCreate);