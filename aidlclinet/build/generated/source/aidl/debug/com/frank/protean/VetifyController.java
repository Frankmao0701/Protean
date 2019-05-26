/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\workspace\\Protean\\aidlclinet\\src\\main\\aidl\\com\\frank\\protean\\VetifyController.aidl
 */
package com.frank.protean;
public interface VetifyController extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.frank.protean.VetifyController
{
private static final java.lang.String DESCRIPTOR = "com.frank.protean.VetifyController";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.frank.protean.VetifyController interface,
 * generating a proxy if needed.
 */
public static com.frank.protean.VetifyController asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.frank.protean.VetifyController))) {
return ((com.frank.protean.VetifyController)iin);
}
return new com.frank.protean.VetifyController.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getVetify:
{
data.enforceInterface(DESCRIPTOR);
com.frank.protean.Vetify _result = this.getVetify();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_addVetifyInOut:
{
data.enforceInterface(DESCRIPTOR);
com.frank.protean.Vetify _arg0;
if ((0!=data.readInt())) {
_arg0 = com.frank.protean.Vetify.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.addVetifyInOut(_arg0);
reply.writeNoException();
if ((_arg0!=null)) {
reply.writeInt(1);
_arg0.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_registerListener:
{
data.enforceInterface(DESCRIPTOR);
com.frank.protean.IVetifyBackListener _arg0;
_arg0 = com.frank.protean.IVetifyBackListener.Stub.asInterface(data.readStrongBinder());
this.registerListener(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unRegisterListener:
{
data.enforceInterface(DESCRIPTOR);
com.frank.protean.IVetifyBackListener _arg0;
_arg0 = com.frank.protean.IVetifyBackListener.Stub.asInterface(data.readStrongBinder());
this.unRegisterListener(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.frank.protean.VetifyController
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public com.frank.protean.Vetify getVetify() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.frank.protean.Vetify _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getVetify, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.frank.protean.Vetify.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void addVetifyInOut(com.frank.protean.Vetify vetify) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((vetify!=null)) {
_data.writeInt(1);
vetify.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addVetifyInOut, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
vetify.readFromParcel(_reply);
}
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void registerListener(com.frank.protean.IVetifyBackListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unRegisterListener(com.frank.protean.IVetifyBackListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unRegisterListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getVetify = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_addVetifyInOut = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registerListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unRegisterListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public com.frank.protean.Vetify getVetify() throws android.os.RemoteException;
public void addVetifyInOut(com.frank.protean.Vetify vetify) throws android.os.RemoteException;
public void registerListener(com.frank.protean.IVetifyBackListener listener) throws android.os.RemoteException;
public void unRegisterListener(com.frank.protean.IVetifyBackListener listener) throws android.os.RemoteException;
}
