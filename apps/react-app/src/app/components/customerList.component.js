import React from 'react';
import httpService from '../services/httpService';
import { Row, Col, Container, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class CustomerList extends React.Component {
  constructor(props) {
    super(props);

    this.getCustomerList = this.getCustomerList.bind(this);
    this.deleteCustomer = this.deleteCustomer.bind(this);

    this.state = {
      customers: [],
    };
  }

  getCustomerList() {
    httpService
      .get('/customers')
      .then((response) => {
        console.log('getCustomerList Response :');
        console.log(response.data);

        this.setState({
          customers: response.data,
        });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  deleteCustomer(id, e) {
    httpService
      .delete('/customers/' + id)
      .then((response) => {
        console.log('deleteCustomer Response :');
        console.log(response);
        this.getCustomerList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  componentDidMount() {
    this.getCustomerList();
  }

  render() {
    console.log(this.state);
    const customerList = this.state.customers.map((customer, index) => {
      let store = customer.store;
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
            <Col>{customer.customerID}</Col>
            <Col>
              {customer.firstName} {customer.lastName}
            </Col>
            <Col>{customer.products.length}</Col>
            <Col>
              {store.storeID} {store.name}
            </Col>
            <Col>
              <Link
                to={{
                  pathname: '/update',
                  state: {
                    id: customer.customerID,
                  },
                }}
              >
                <Button variant="dark">Update</Button>
              </Link>{' '}
              <Button
                variant="light"
                onClick={(e) => this.deleteCustomer(customer.customerID, e)}
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
        <h3 style={{ textAlign: 'center' }}>Customer List</h3>
        <Link to={'/create'}>
          <Button variant="outline-dark">Add new Customer</Button>
        </Link>
        <Container>
          <Row>
            <Col>Customer ID</Col>
            <Col>Name</Col>
            <Col>Products</Col>
            <Col>Store</Col>
            <Col>Actions</Col>
          </Row>
          {customerList}
        </Container>
      </Container>
    );
  }
}
