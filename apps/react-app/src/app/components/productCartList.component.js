import React from 'react';
import httpService from '../services/httpService';
import { Row, Col, Container, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class ProductCartList extends React.Component {
  constructor(props) {
    super(props);

    this.getProductCartList = this.getProductCartList.bind(this);
    this.deleteProductCart = this.deleteProductCart.bind(this);

    this.state = {
      productCarts: [],
    };
  }

  getProductCartList() {
    httpService
      .get('/productCarts')
      .then((response) => {
        console.log('getProductCartList Response :');
        console.log(response.data);

        this.setState({
          productCarts: response.data,
        });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  deleteProductCart(id, e) {
    httpService
      .delete('/productCarts/' + id)
      .then((response) => {
        console.log('deleteProductCart Response :');
        console.log(response);
        this.getProductCartList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  componentDidMount() {
    this.getProductCartList();
  }

  render() {
    console.log(this.state);
    const productCartList = this.state.productCarts.map((productCart, index) => {
      let customer = productCart.customer;
      if (!customer) {
        customer = {
          customerID: 0,
          firstName: '',
          lastName: '',
        };
      }

      let product = productCart.product;
      if (!product) {
        product = {
          productID: 0,
          name: '',
          brand: '',
        };
      }

      return (
        <Container>
          <br />
          <Row key={index}>
            <Col>{productCart.id.productID} {productCart.id.customerID}</Col>
            <Col>
              {customer.firstName} {customer.lastName}
            </Col>
            <Col>
              {product.brand} {product.name}
            </Col>
            <Col>
              {productCart.amount}
            </Col>
            <Col>
              <Link
                to={{
                  pathname: '/update',
                  state: {
                    id: productCart.id,
                  },
                }}
              >
                <Button variant="dark">Update</Button>
              </Link>{' '}
              <Button
                variant="light"
                onClick={(e) => this.deleteProductCart(productCart.id, e)}
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
        <h3 style={{ textAlign: 'center' }}>ProductCart List</h3>
        <Link to={'/create'}>
          <Button variant="outline-dark">Add new ProductCart</Button>
        </Link>
        <Container>
          <Row>
            <Col>Product Cart ID</Col>
            <Col>Customer</Col>
            <Col>Product</Col>
            <Col>Amount</Col>
            <Col>Actions</Col>
          </Row>
          {productCartList}
        </Container>
      </Container>
    );
  }
}
