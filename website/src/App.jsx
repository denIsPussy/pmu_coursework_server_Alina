import './App.css';
import { useRoutes, Outlet, BrowserRouter } from 'react-router-dom';
import Header from './components/common/Header';
import Footer from './components/common/Footer';
import Basket from './components/catalogs/Home';
import FlowerCatalog from './components/catalogs/FlowerCatalog';
import Contacts from './components/catalogs/Contacts';
import Registration from './components/catalogs/Registration';
import Provider from './components/catalogs/Providers';
import Dilivery from './components/catalogs/Dilivery';
import Order from './components/catalogs/Orders';

function Router(props) {
  return useRoutes(props.rootRoute);
}

export default function App() {
  const routes = [
    { index: true, element: <Basket /> },

    { path: 'catalogs/basket', element: <Basket />, label: 'Главная' },
    { path: 'catalogs/registration', element: <Registration />, label: 'О нас' },
    { path: 'catalogs/catalog', element: <FlowerCatalog />, label: 'Каталог' },
    { path: 'catalogs/order', element: <Order />, label: 'Заказы' },
    { path: 'catalogs/contacts', element: <Contacts />, label: 'Статьи' },
    { path: 'catalogs/providers', element: <Provider />, label: 'Сотрудничество' },
    { path: 'catalogs/dilivery', element: <Dilivery />, label: 'Доставка и оплата' },
  ];
  const links = routes.filter(route => route.hasOwnProperty('label'));
  const rootRoute = [
    { path: '/', element: render(links), children: routes }
  ];

  function render(links) {
    return (
      <>
        <Header links={links} />
        <div className="container-fluid p-0" style={{  marginBottom: '50px'}}>
          <Outlet />
        </div>
      </>
    );
  }

  return (
    <BrowserRouter>
      <Router rootRoute={rootRoute} />
    </BrowserRouter>
  );
}