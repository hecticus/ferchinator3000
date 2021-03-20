import { Observable } from 'rxjs';
import { Resultado } from 'src/domain/resultado';

export abstract class StorageGateway {
    public abstract put(name: string, value: any): any;
    public abstract get(name: string): any;
    public abstract delete(name: string): any;
}
