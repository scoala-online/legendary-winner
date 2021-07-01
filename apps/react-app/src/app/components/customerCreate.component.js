import React from 'react';
import httpService from '../services/httpService';
import { Row, Col, Container, Button } from 'react-bootstrap';
import { Link, withRouter } from 'react-router-dom';

class CustomerCreate extends React.Component {
  constructor(props) {
    super(props);

    this.createCustomer = this.createCustomer.bind(this);
    this.getSupervisorList = this.getSupervisorList.bind(this);

    this.state = {
      firstName: '',
      lastName: '',
      products: [],
    };
  }

  createCustomer(customer) {
    let data = {
      firstName: customer.firstName,
      lastName: customer.lastName,
      products: customer.products,
    };

    console.log(data);

    httpService
      .post('/customers', data)
      .then((response) => {
        console.log('createCustomer Response :');
        console.log(response.data);
        this.props.history.push('/customers');
      })
      .catch((e) => {
        console.log(e);
      });
  }

  render() {
    const customer = this.state;

    return (
      <Container>
        <h2>Create Customer</h2>
        <br />
        <Container>
          <Row>
            <Col>First Name:</Col>
            <Col>
              <input
                type="text"
                placeholder={customer.firstName}
                required
                onChange={(e) => this.setState({ firstName: e.target.value })}
              />
            </Col>
          </Row>
          <Row>
            <Col>Last Name:</Col>
            <Col>
              <input
                type="text"
                placeholder={customer.lastName}
                required
                onChange={(e) => this.setState({ lastName: e.target.value })}
              />
            </Col>
          </Row>
          <br />
          <Link to={'/customers'}>
            <Button variant="outline-primary">Back to Customers</Button>
          </Link>
          <Button
            variant={'success'}
            style={{ marginLeft: '5vw' }}
            onClick={() => this.createCustomer(customer)}
          >
            Create
          </Button>
        </Container>
      </Container>
    );
  }
}

export default withRouter(CustomerCreate);
