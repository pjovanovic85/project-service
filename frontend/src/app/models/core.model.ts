export class PageInfo {
  pageable?: Pageable;
  last?: boolean;
  totalElements?: number;
  totalPages?: number;
  size?: number;
  number?: number;
  sort?: any;
  numberOfElements?: number;
  first?: boolean;
  empty?: boolean;
}
export class Pageable {
  sort: Sort;
  offset: number;
  pageSize: number;
  pageNumber: number;
  paged: boolean;
  unpaged: boolean;
}
export class Sort {
  sorted?: boolean;
  unsorted?: boolean;
  empty?: boolean;
}
export class RespObjPaged<T> extends PageInfo {
  content?: T[];
}

export class SearchQuery {
  page? = 1;
  limit? = 40;
  sort?: string = '';
}

export class PagedEvent {
  page: number;
  sort: string;
}

export interface TableConfig {
  columns?: TableColumns[];
  actions?: string[];
  label?: string;
  type?: string;
  id?: number;
}

export interface TableColumns {
  field: string;
  header: string;
  name?: string;
  type?: 'date' | 'currency' | 'dateTime' | 'time';
  childField?: string;
  formatedValue?: (x: string | number) => string | number;
  width?: number;
  show?: boolean;
  sticky?: boolean;
  scroll?: boolean;
  disableFilter?: boolean;
  fieldConfig?: {
    fieldType?: 'select';
    options?: any[];
  };
}

export interface CrudTableConfig {
  columns?: CrudTableColumns[];
  actions?: string[];
  label?: string;
  type?: string;
}

export interface CrudTableColumns {
  field?: string;
  header?: string;
  width?: number;
  fieldConfig?: {
    readonly?: boolean;
    fieldType?: 'text' | 'select' | 'number' | 'radio' | 'checkbox' | 'datepicker' | 'expression' | 'selectFieldType';
    options?: any[];
  };
}

export class PageQuery {
  page? = new Page();
  search?: { [key: string]: string } = {};
  order? = 'desc';
  sortBy? = 'idOperation';
}

export class Page {
  pageSize = 75;
  pageIndex = 0;
}

export interface SearchEvent {
  active: string;
  direction: string;
}

export interface PageEvent {
  length: number;
  pageIndex: number;
  pageSize: number;
  previousPageIndex: number;
}
export interface TabsConfig {
  columns?: CrudTableColumns[];
  label: string;
  type?: string;
}
