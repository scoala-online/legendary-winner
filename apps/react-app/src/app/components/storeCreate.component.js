import React from 'react';
import httpService from '../services/httpService';
import { Row, Col, Container, Button } from 'react-bootstrap';
import { Link, withRouter } from 'react-router-dom';

class StoreCreate extends React.Component {
  constructor(props) {
    super(props);

    this.createStore = this.createStore.bind(this);
    this.getProductList = this.getProductList.bind(this);
    this.getCustomerList = this.getCustomerList.bind(this);

    this.state = {
      name: '',
      address: '',
      customers: [],
      products: [],
    };
  }

  createStore(store) {
    let data = {
      name: store.name,
      address: store.address,
      customers: store.customers,
      products: store.products,
    };

    console.log(data);

    httpService
      .post('/stores', data)
      .then((response) => {
        console.log('createStore Response :');
        console.log(response.data);
        this.props.history.push('/stores');
      })
      .catch((e) => {
        console.log(e);
      });
  }

  getProductList() {
    httpService
      .get('/products')
      .then((response) => {
        console.log('getProductList Response :');
        console.log(response.data);
        this.setState({ products: response.data });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  getCustomerList() {
    httpService
      .get('/customers')
      .then((response) => {
        console.log('getCustomerList Response :');
        console.log(response.data);
        this.setState({ customers: response.data });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  componentDidMount() {
    this.getProductList();
    this.getCustomerList();
  }

  render() {
    const store = this.state;

    return (
      <Container>
        <h2>Create Store</h2>
        <br />
        <Container>
          <Row>
            <Col>Name:</Col>
            <Col>
              <input
                type="text"
                placeholder={store.name}
                required
                onChange={(e) => this.setState({ name: e.target.value })}
              />
            </Col>
          </Row>
          <Row>
            <Col>Address:</Col>
            <Col>
              <input
                type="text"
                placeholder={store.address}
                required
                onChange={(e) => this.setState({ address: e.target.value })}
              />
            </Col>
          </Row>
          <br />
          <Link to={'/stores'}>
            <Button variant="outline-primary">Back to Stores</Button>
          </Link>
          <Button
            variant={'success'}
            style={{ marginLeft: '5vw' }}
            onClick={() => this.createStore(store)}
          >
            Create
          </Button>
        </Container>
      </Container>
    );
  }
}

export default withRouter(StoreCreate);
