import React from 'react';
import httpService from '../services/httpService';
import { Row, Col, Container, Button } from 'react-bootstrap';
import { Link, withRouter } from 'react-router-dom';

class StoreCreate extends React.Component {
  constructor(props) {
    super(props);

    this.createStore = this.createStore.bind(this);
    this.getSupervisorList = this.getSupervisorList.bind(this);

    this.state = {
      name: '',
      address: '',
      customers: [],
      products: []
    };
  }

  createStore(store) {
    let data = {
      name: store.name,
      address: store.address,
      customers: store.customers,
      products: store.products
    };

    if (store.supervisorId !== 0) {
      console.log(store.supervisorId);
      data = {
        ...data,
        supervisor: {
          id: store.supervisorId,
        },
      };
    } else {
      data = {
        ...data,
        supervisor: null,
      };
    }

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
