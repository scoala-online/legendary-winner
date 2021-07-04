import React from 'react';
import httpService from '../services/httpService';
import { Row, Col, Container, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class ProductCartKeyList extends React.Component {
  constructor(props) {
    super(props);

    this.getProductCartKeyList = this.getProductCartKeyList.bind(this);
    this.deleteProductCartKey = this.deleteProductCartKey.bind(this);

    this.state = {
      productCartKeys: [],
    };
  }

  getProductCartKeyList() {
    httpService
      .get('/productCartKeys')
      .then((response) => {
        console.log('getProductCartKeyList Response :');
        console.log(response.data);

        this.setState({
          productCartKeys: response.data,
        });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  deleteProductCartKey(id, e) {
    httpService
      .delete('/productCartKeys/' + id)
      .then((response) => {
        console.log('deleteProductCartKey Response :');
        console.log(response);
        this.getProductCartKeyList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  componentDidMount() {
    this.getProductCartKeyList();
  }

  render() {
    console.log(this.state);
    const productCartKeyList = this.state.productCartKeys.map(
      (productCartKey, index) => {
        return (
          <Container>
            <br />
            <Row key={index}>
              <Col>{productCartKey.productID}</Col>
              <Col>{productCartKey.customerID}</Col>
              <Col>
                <Link
                  to={{
                    pathname: '/update',
                    state: {
                      id: productCartKey.id,
                    },
                  }}
                >
                  <Button variant="dark">Update</Button>
                </Link>{' '}
                <Button
                  variant="light"
                  onClick={(e) =>
                    this.deleteProductCartKey(productCartKey.id, e)
                  }
                >
                  Delete
                </Button>
              </Col>
            </Row>
          </Container>
        );
      }
    );

    return (
      <Container>
        <h3 style={{ textAlign: 'center' }}>ProductCartKey List</h3>
        <Link to={'/create'}>
          <Button variant="outline-dark">Add new ProductCartKey</Button>
        </Link>
        <Container>
          <Row>
            <Col>Product ID</Col>
            <Col>Customer ID</Col>
            <Col>Actions</Col>
          </Row>
          {productCartKeyList}
        </Container>
      </Container>
    );
  }
}
